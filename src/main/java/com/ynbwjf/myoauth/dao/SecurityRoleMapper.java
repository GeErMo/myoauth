package com.ynbwjf.myoauth.dao;

import com.ynbwjf.myoauth.entity.SecurityRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SecurityRole record);

    int insertSelective(SecurityRole record);

    SecurityRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecurityRole record);

    int updateByPrimaryKey(SecurityRole record);

    List<SecurityRole> selectAllRoles();


}