package com.ynbwjf.myoauth.security;

import com.alibaba.fastjson.JSON;
import com.ynbwjf.myoauth.entity.SecurityResource;
import com.ynbwjf.myoauth.service.SecurityResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Program: myoauth
 * @description: 用户登录成功所执行的处理类
 * @Author: zrj
 * @Date: 2018-09-06 14:09
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private SecurityResourceService securityResourceService;
    private static Logger LOG = LoggerFactory.getLogger(LoginSuccessHandler.class);
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        LOG.info("用户ip"+getIpAddress(request));
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        request.getSession().setAttribute("user",customUserDetails.getUsername());
        List<SecurityResource> securityResources = securityResourceService.selectResourceByUserId(customUserDetails.getId());//查询用户所有资源权限
        LOG.info("所有权限" + JSON.toJSONString(securityResources));
        HttpSession session = request.getSession();//获取session服务
        StringBuffer sb = new StringBuffer();
        Map<String, Object> data = new HashMap<>();
        if (securityResources != null) {
            for (int i = 0; i < securityResources.size(); i++) {
                data.put("" + securityResources.get(i).getId(), securityResources.get(i).getId());
                sb.append("" + securityResources.get(i).getId() + ",");
            }
        }
        session.setAttribute("userOperationsMap", data);
        super.onAuthenticationSuccess(request, response, authentication);
    }
    public String getIpAddress(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
