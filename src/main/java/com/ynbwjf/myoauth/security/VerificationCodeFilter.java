package com.ynbwjf.myoauth.security;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Program: myoauth
 * @description: 验证码校验过滤器
 * @Author: zrj
 * @Date: 2018-09-07 15:45
 */
public class VerificationCodeFilter extends AbstractAuthenticationProcessingFilter {
    private static Logger LOG = LoggerFactory.getLogger(VerificationCodeFilter.class);

    private String defaultFilterProcessesUrl;
    private boolean checkValidateCode = true;
    private String validateCodeParameter = "verifyCode";
    private String validateCodeSessionName = "verifyCode";

    public VerificationCodeFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
        this.defaultFilterProcessesUrl = defaultFilterProcessesUrl;
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/loginPage"));
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        if ("POST".equalsIgnoreCase(request.getMethod()) && defaultFilterProcessesUrl.equals(request.getServletPath())) {
            LOG.info("开始验证码校验");
            if(checkValidateCode){
                checkValidateCode(request,response);
            }
        }
        chain.doFilter(req, res);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        return null;
    }

    /**
     * 匹配验证码规则
     * @param request
     */
    protected void checkValidateCode(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
        String sessionValidateCode = obtainSessionValidateCode(request);
        String validateCodeParameter = obtainValidateCodeParameter(request);
        // 验证码输入不能为空，不区分大小写
        if (StringUtils.isBlank(validateCodeParameter) || !sessionValidateCode.equalsIgnoreCase(validateCodeParameter)) {
            unsuccessfulAuthentication(request,response,new InsufficientAuthenticationException("验证码不正确"));
        }
    }
        private String obtainValidateCodeParameter(HttpServletRequest request) {
            return request.getParameter(validateCodeParameter);
        }

        protected String obtainSessionValidateCode(HttpServletRequest request) {
            Object sessionCode = request.getSession().getAttribute(validateCodeSessionName);
            return null == sessionCode ? "" : sessionCode.toString();
        }
}
