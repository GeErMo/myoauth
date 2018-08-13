package com.ynbwjf.myoauth.dao;

import com.ynbwjf.myoauth.entity.SecurityRoleToResource;

public interface SecurityRoleToResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SecurityRoleToResource record);

    int insertSelective(SecurityRoleToResource record);

    SecurityRoleToResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecurityRoleToResource record);

    int updateByPrimaryKey(SecurityRoleToResource record);
}