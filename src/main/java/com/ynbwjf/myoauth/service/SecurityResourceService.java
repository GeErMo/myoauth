package com.ynbwjf.myoauth.service;

import com.ynbwjf.myoauth.entity.SecurityResource;

import java.util.List;

/**
 * @Program: myoauth
 * @description: 角色资源权限业务逻辑处理接口
 * @Author: zrj
 * @Date: 2018-08-06 16:03
 */
public interface SecurityResourceService {
    /**
     * 查询所有资源权限
     * @return
     */
    List<SecurityResource> selectAllResource();
    /**
     * 通过用户id查询资源权限信息
     *
     * @param userId
     * @return
     */
    public List<SecurityResource> selectResourceByUserId(Long userId);
}
