package com.ynbwjf.myoauth.serviceImpl;

import com.ynbwjf.myoauth.dao.SecurityUserMapper;
import com.ynbwjf.myoauth.entity.SecurityUser;
import com.ynbwjf.myoauth.service.SecurityUserService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserServiceImpl implements SecurityUserService {
    @Autowired
    private SecurityUserMapper securityUserMapper;
    private SecurityUser securityUser1;

    @Override
    public SecurityUser selectUserByUserName(String username) {
        securityUser1 = securityUserMapper.selectUserByUsername(username);
        return securityUser1;
    }
}
