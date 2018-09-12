package com.ynbwjf.myoauth.security;

import com.alibaba.fastjson.JSON;
import com.ynbwjf.myoauth.entity.SecurityResource;
import com.ynbwjf.myoauth.entity.SecurityRole;
import com.ynbwjf.myoauth.service.SecurityResourceService;
import com.ynbwjf.myoauth.service.SecurityRoleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 资源权限检查
 */
@Component
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
    @Autowired
    private SecurityRoleService securityRoleService;
    @Autowired
    private SecurityResourceService securityResourceService;

    private static final Logger LOG = LoggerFactory.getLogger(CustomSecurityMetadataSource.class);
    private static Map<String,Collection<ConfigAttribute>> RESOURCE_ROLE_MAP = null;
    private static Collection<ConfigAttribute> ALL_CONFIG_ATTRIBUTES = null;
    private static Map<String, ConfigAttribute> ALL_CONFIG_ATTRIBUTES_MAP = null;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        LOG.info("计算角色资源权限开始");
        LOG.info(JSON.toJSONString(ALL_CONFIG_ATTRIBUTES));
        FilterInvocation fi = (FilterInvocation) o;
        HttpServletRequest request = fi.getRequest();
        LOG.info("当前请求地址"+fi.getRequestUrl());
        if(RESOURCE_ROLE_MAP == null){
            getResourceRoleMap();//计算资源
        }
        if(ALL_CONFIG_ATTRIBUTES_MAP == null){
            getAllConfigAttributes();
        }
        Boolean ok = false;
        if (RESOURCE_ROLE_MAP != null){
            Iterator<String> iterator = RESOURCE_ROLE_MAP.keySet().iterator();
            while (iterator.hasNext()) {
                String ResourceUrl = iterator.next();
                RequestMatcher requestMatcher = new AntPathRequestMatcher(ResourceUrl);
                if(requestMatcher.matches(request)){
                    LOG.info("匹配成功");
                    Collection<ConfigAttribute> config = RESOURCE_ROLE_MAP.get(ResourceUrl);
                    LOG.info(JSON.toJSONString(config));
                    LOG.info("找到此项权限!!!");
                    LOG.info(ResourceUrl);
                    if(CollectionUtils.isEmpty(config)){
                        LOG.info("返回空值");
                        // throw new AccessDeniedException("ROLE_NO_USER");
                        Collection<ConfigAttribute> returnCollectionFind = new ArrayList<ConfigAttribute>();
//                        returnCollectionFind.add(new SecurityConfig("ROLE_ANONYMOUS"));
                        return returnCollectionFind;
                    }
                    LOG.info("资源权限检查结束");
                    ok = true;
                    return config;
                }
            }
        }
        LOG.info("未找到此项权限!");
        Collection<ConfigAttribute> returnCollection = new ArrayList<ConfigAttribute>();
//        returnCollection.add(new SecurityConfig("ROLE_ANONYMOUS"));
        LOG.info("资源权限检查结束");
        return returnCollection;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        LOG.info("getAllConfigAttributes");
        LOG.info("取得所有权限...计算哪些角色具有该资源的权限  getAllConfigAttributes");
        if(ALL_CONFIG_ATTRIBUTES == null){
            ALL_CONFIG_ATTRIBUTES = getAllConfigAttributesMap().values();
            LOG.info("****************************");
            LOG.info("ALL_CONFIG_ATTRIBUTES=");
            LOG.info(JSON.toJSONString(ALL_CONFIG_ATTRIBUTES));
            LOG.info("****************************");
        }
        return ALL_CONFIG_ATTRIBUTES;
    }
    public void initResource() {

        LOG.info("initResource()");
        RESOURCE_ROLE_MAP = null;
        this.getResourceRoleMap();
    }
    public void clear() {
        LOG.info("清空缓存权限..........");
        RESOURCE_ROLE_MAP = null;
        ALL_CONFIG_ATTRIBUTES = null;
        ALL_CONFIG_ATTRIBUTES_MAP = null;
    }
    public static void reloadAuthed() {
        LOG.info("重置清空缓存权限..... ");
        RESOURCE_ROLE_MAP = null;
        ALL_CONFIG_ATTRIBUTES = null;
        ALL_CONFIG_ATTRIBUTES_MAP = null;
        if (RESOURCE_ROLE_MAP == null) {
            // getResourceRoleMapReload();
        }
        if (ALL_CONFIG_ATTRIBUTES_MAP == null) {
            // getAllConfigAttributesReload();
        }
        LOG.info("重置ok...");
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * 获取所有角色
     * @return
     */
    private Map<String, ConfigAttribute> getAllConfigAttributesMap(){
        if(ALL_CONFIG_ATTRIBUTES_MAP == null){
            ALL_CONFIG_ATTRIBUTES_MAP = new HashMap<>();
            //获取所有角色
            List<SecurityRole> securityRoles = securityRoleService.selectAllRoles();
            LOG.info("获取所有角色:");
            LOG.info(JSON.toJSONString(securityRoles));
            if(!CollectionUtils.isEmpty(securityRoles)){
                for (SecurityRole securityRole : securityRoles) {
                    ConfigAttribute ca = new SecurityConfig(securityRole.getId().toString());
                    ALL_CONFIG_ATTRIBUTES_MAP.put(securityRole.getId().toString(), ca);
                }
            }
        }
        return ALL_CONFIG_ATTRIBUTES_MAP;

    }

    /**
     * 获取资源
     * @return
     */
    private Map<String, Collection<ConfigAttribute>> getResourceRoleMap(){
        if(RESOURCE_ROLE_MAP == null){
            System.out.println("*获取资源+角色 RESOURCE_ROLE_MAP**************");
            RESOURCE_ROLE_MAP = new HashMap<>();
            List<SecurityResource> resources = securityResourceService.selectAllResource();
            System.out.println("resources:"+resources.size());
            LOG.info("用户的所有资源:" + JSON.toJSONString(resources));
            if (!CollectionUtils.isEmpty(resources)) {
                LOG.info("进来了");
                for (SecurityResource resource : resources) {
                    if (StringUtils.isBlank(resource.getResourcePath())) {
                        continue;
                    }
                    Collection<ConfigAttribute> cas = new ArrayList<>();
                    // 这里是查询角色服务
                    List<Long> roleIds = securityRoleService.selectRoleIdsByResourceId(resource.getId());
                    if (!CollectionUtils.isEmpty(roleIds)) {
                        roleIds.forEach(roleId -> {
                            ConfigAttribute ca = getAllConfigAttributesMap().get(roleId.toString());
                            if (ca != null) {
                                cas.add(ca);
                            }
                        });
                    }
                    RESOURCE_ROLE_MAP.put(resource.getResourcePath(), cas);
                }
            }
        }
        return RESOURCE_ROLE_MAP;

    }
}
