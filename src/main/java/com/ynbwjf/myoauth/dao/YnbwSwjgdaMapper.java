package com.ynbwjf.myoauth.dao;

import com.ynbwjf.myoauth.entity.YnbwSwjgda;

public interface YnbwSwjgdaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(YnbwSwjgda record);

    int insertSelective(YnbwSwjgda record);

    YnbwSwjgda selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(YnbwSwjgda record);

    int updateByPrimaryKey(YnbwSwjgda record);
}