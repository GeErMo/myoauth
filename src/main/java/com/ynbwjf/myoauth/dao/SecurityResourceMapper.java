package com.ynbwjf.myoauth.dao;

import com.ynbwjf.myoauth.entity.SecurityResource;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SecurityResourceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SecurityResource record);

    int insertSelective(SecurityResource record);

    SecurityResource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SecurityResource record);

    int updateByPrimaryKey(SecurityResource record);
    List<SecurityResource> selectAllResource();
}