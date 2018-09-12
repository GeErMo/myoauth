package com.ynbwjf.myoauth.security;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Program: myoauth
 * @description: 验证主题是否允许调用权限
 * @Author: zrj
 * @Date: 2018-08-06 17:08
 */
@Component
public class CustomAccessDecisionManger implements AccessDecisionManager {
    private static final Logger LOG = LoggerFactory.getLogger(CustomAccessDecisionManger.class);
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> configAttributes ) throws AccessDeniedException, InsufficientAuthenticationException {

        FilterInvocation fi = (FilterInvocation) o;
        LOG.info("AccessDecisionManager decide url=="+fi.getRequestUrl());
        LOG.info("该请求所需权限:"+configAttributes);
        LOG.info("用户拥有权限:"+authentication.getAuthorities());
        if(!CollectionUtils.isEmpty(configAttributes) && !CollectionUtils.isEmpty(authentication.getAuthorities())){
            for(ConfigAttribute ca : configAttributes){
                if(StringUtils.isBlank(ca.getAttribute())){
                    continue;
                }
                for(GrantedAuthority ga : authentication.getAuthorities()){
                    if(ca.getAttribute().equals(ga.getAuthority())){
                        return;
                    }
                }
            }
        }
        LOG.info("AccessDecisionManager decide 结束");
        throw new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
