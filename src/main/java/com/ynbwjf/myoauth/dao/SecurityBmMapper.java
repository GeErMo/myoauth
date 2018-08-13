package com.ynbwjf.myoauth.dao;

import com.ynbwjf.myoauth.entity.SecurityBm;

public interface SecurityBmMapper {
    int deleteByPrimaryKey(Long bmId);

    int insert(SecurityBm record);

    int insertSelective(SecurityBm record);

    SecurityBm selectByPrimaryKey(Long bmId);

    int updateByPrimaryKeySelective(SecurityBm record);

    int updateByPrimaryKey(SecurityBm record);
}