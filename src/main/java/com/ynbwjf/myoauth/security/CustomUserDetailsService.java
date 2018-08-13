package com.ynbwjf.myoauth.security;

import com.ynbwjf.myoauth.entity.SecurityRole;
import com.ynbwjf.myoauth.entity.SecurityUser;
import com.ynbwjf.myoauth.service.SecurityRoleService;
import com.ynbwjf.myoauth.service.SecurityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private SecurityUserService securityUserService;
    @Autowired
    private SecurityRoleService securityRoleService;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查询用户信息
        SecurityUser securityUser = securityUserService.selectUserByUserName(username);
        if (securityUser == null){
            throw new AuthenticationServiceException("账号:"+username+"不存在");
        }else {
            if (securityUser.getDwId() == null && "".equals(securityUser.getDwId())){
                if(securityUser.getSwjgbm() == null && "".equals(securityUser.getSwjgbm())){
                    throw new AuthenticationServiceException("您无权访问");
                }
            }
        }
        //查询用户角色信息
        List<SecurityRole> securityRoles = securityRoleService.selectRolesByUserId(securityUser.getId());
        ArrayList<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
        if(!CollectionUtils.isEmpty(securityRoles)){
            for (SecurityRole role : securityRoles) {
                GrantedAuthority authority = new SimpleGrantedAuthority(role.getId().toString());
                gas.add(authority);
            }
        }
        CustomUserDetails customUserDetails = new CustomUserDetails(securityUser.getUsername(), securityUser.getPassword(), gas, securityUser.getAccountnonexpired(), securityUser.getAccountnonlocked(), securityUser.getCredentialsnonexpired(), securityUser.getEnabled(), securityUser.getXm(), securityUser.getDzyx(), securityUser.getId(), securityUser.getDwId(), securityUser.getBmId(),securityUser.getSwjgbm(),securityUser.getDh(), securityUser.getIsLimit());
        return customUserDetails;
    }
}
