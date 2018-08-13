package com.ynbwjf.myoauth.dao;

import com.ynbwjf.myoauth.entity.SecurityDw;

public interface SecurityDwMapper {
    int deleteByPrimaryKey(Long dwId);

    int insert(SecurityDw record);

    int insertSelective(SecurityDw record);

    SecurityDw selectByPrimaryKey(Long dwId);

    int updateByPrimaryKeySelective(SecurityDw record);

    int updateByPrimaryKey(SecurityDw record);
}