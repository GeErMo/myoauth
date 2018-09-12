package com.ynbwjf.myoauth.service;

import com.ynbwjf.myoauth.entity.SecurityRole;

import java.util.List;

public interface SecurityRoleService {
    /**
     * 通过用户ID查询角色信息
     * @param userId
     * @return
     */
    List<SecurityRole> selectRolesByUserId(Long userId);

    /**
     * 查询所有角色信息
     * @return
     */
    List<SecurityRole> selectAllRoles();

    /**
     * 通过资源id查询所有角色id信息
     * @param resourceId
     * @return
     */
    List<Long> selectRoleIdsByResourceId(Long resourceId);


}
