package com.ynbwjf.myoauth.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;

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
public class CustomSecurityFilter extends AbstractSecurityInterceptor implements Filter {
    @Autowired
    private CustomSecurityMetadataSource customSecurityMetadataSource;
    private static final Logger LOG = LoggerFactory.getLogger(CustomSecurityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("初始化Security过滤器");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        LOG.info("SecurityFilter 开始 ...." );
        LOG.info("userName:"+getPrincipal());
        if(getPrincipal().equals("anonymousUser")){
            //throw new AuthenticationException("出错:anonymousUser");
            throw new BadCredentialsException("err:anonymousUser!" );

        }
        FilterInvocation fi = new FilterInvocation(servletRequest, servletResponse, filterChain);
    }

    //获取缓存用户对象
    private String getPrincipal(){
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    public void invoke(FilterInvocation fi) throws IOException,ServletException{
        InterceptorStatusToken interceptorStatusToken = super.beforeInvocation(fi);
        try {
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

    public CustomSecurityMetadataSource getCustomSecurityMetadataSource() {
        return customSecurityMetadataSource;
    }

    public void setCustomSecurityMetadataSource(CustomSecurityMetadataSource customSecurityMetadataSource) {
        this.customSecurityMetadataSource = customSecurityMetadataSource;
    }
}
