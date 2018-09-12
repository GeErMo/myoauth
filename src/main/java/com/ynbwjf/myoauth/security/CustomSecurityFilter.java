package com.ynbwjf.myoauth.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;
import java.util.logging.LogRecord;

/**
 * @Program: myoauth
 * @description: 将资源和权限装配到hashMap中, 供Spring security使用
 * @Author: zrj
 * @Date: 2018-08-06 17:22
 */
@Component
public class CustomSecurityFilter extends AbstractSecurityInterceptor implements Filter {
    @Autowired
    private CustomSecurityMetadataSource customSecurityMetadataSource;

    private static final Logger LOG = LoggerFactory.getLogger(CustomSecurityFilter.class);

    @Autowired
    public void setAccessDecisionManager(CustomAccessDecisionManger customAccessDecisionManager) {
        super.setAccessDecisionManager(customAccessDecisionManager);
    }
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.info("初始化Security过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.info("SecurityFilter 开始 ...." );
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
        LOG.info("requestURL:" + fi.getRequestUrl());
        invoke(fi);
    }

    public void invoke(FilterInvocation fi) throws IOException,ServletException{
        InterceptorStatusToken interceptorStatusToken = super.beforeInvocation(fi);
        try {
            System.out.println("执行下一个拦截器");
            fi.getChain().doFilter(fi.getRequest(),fi.getResponse());
        }finally {
            super.afterInvocation(interceptorStatusToken,null);
        }
    }

    @Override
    public void destroy() {
        System.out.println("销毁Security过滤器");
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.customSecurityMetadataSource;
    }

}
