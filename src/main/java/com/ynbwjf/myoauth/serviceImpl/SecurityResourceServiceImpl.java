package com.ynbwjf.myoauth.serviceImpl;

import com.ynbwjf.myoauth.dao.SecurityResourceMapper;
import com.ynbwjf.myoauth.entity.SecurityResource;
import com.ynbwjf.myoauth.service.SecurityResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Program: myoauth
 * @description: 角色资源权限业务处理实现层
 * @Author: zrj
 * @Date: 2018-08-06 16:05
 */
@Service
public class SecurityResourceServiceImpl implements SecurityResourceService {
    @Autowired
    private SecurityResourceMapper securityResourceMapper;

    @Override
    public List<SecurityResource> selectAllResource() {
        List<SecurityResource> securityResources = securityResourceMapper.selectAllResource();
        return securityResources;
    }
}
