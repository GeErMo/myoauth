package com.ynbwjf.myoauth.dao;

import com.ynbwjf.myoauth.entity.SecurityUserToRole;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SecurityUserToRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SecurityUserToRole record);

    int insertSelective(SecurityUserToRole record);

    SecurityUserToRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecurityUserToRole record);

    int updateByPrimaryKey(SecurityUserToRole record);

    /**
     * 通过用户id查询角色信息集合
     * @param userid
     * @return
     */
    List<SecurityUserToRole> selectUserToRoleByUserId(Long userid);
}