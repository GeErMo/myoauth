package com.ynbwjf.myoauth.dao;

import com.ynbwjf.myoauth.entity.SecurityUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SecurityUser record);

    int insertSelective(SecurityUser record);

    SecurityUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecurityUser record);

    int updateByPrimaryKey(SecurityUser record);

    /**
     * 通过用户名查询用户信息
     * @param username
     * @return
     */
    SecurityUser selectUserByUsername(String username);
}