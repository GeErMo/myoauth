package com.ynbwjf.myoauth.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * @Program: myoauth
 * @description: 身份信息验证
 * @Author: zrj
 * @Date: 2018-08-13 9:22
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static Logger LOG = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LOG.info("验证开始");
        CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(authentication.getName());
        if(customUserDetails == null){
            throw new UsernameNotFoundException("用户名或密码错误！");
        }if(customUserDetails.isAccountNonExpired() == false){
            throw new UsernameNotFoundException("账号已失效！");
        }
        if(customUserDetails.isAccountNonLocked() == false){
            throw new UsernameNotFoundException("账号已锁定！");
        }
        if(customUserDetails.isCredentialsNonExpired() == false){
            throw new UsernameNotFoundException("账号凭证已过期！");
        }
        if(customUserDetails.isEnabled() == false){
            throw new UsernameNotFoundException("账号已禁用！");
        }
        if (authentication.getCredentials()==null){
            throw new BadCredentialsException("请输入密码!");
        }
        if (authentication.getCredentials().equals("")){
            throw new BadCredentialsException("请输入密码!");
        }

        try {
            if(bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), customUserDetails.getPassword())){
                LOG.info("密码正确...");
            }else{
                LOG.info("密码错误...");
                //LOG.info(bCryptPasswordEncoder.encode(authentication.getCredentials().toString()));
                throw new BadCredentialsException("密码错误!");
            }
        }catch (Exception e){
            LOG.info("解码 密码错..........");
            LOG.info(e.toString());
            throw new BadCredentialsException(e.getMessage());
        }

        //设置用户ip地址
        try {
            WebAuthenticationDetails wauth = (WebAuthenticationDetails) authentication.getDetails();
            LOG.info("IP=="+wauth.getRemoteAddress());
            LOG.info("getSessionId=="+wauth.getSessionId());
            customUserDetails.setIp(wauth.getRemoteAddress());
        } catch (Exception e) {
            LOG.info("Error get ip");
            LOG.info(e.toString());
        }
        Authentication authentication1 = new UsernamePasswordAuthenticationToken(customUserDetails, authentication.getCredentials(), customUserDetails.getAuthorities());

        return authentication1;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UsernamePasswordAuthenticationToken.class.equals(aClass);
    }
}
