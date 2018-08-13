package com.ynbwjf.myoauth.serviceImpl;

import com.ynbwjf.myoauth.dao.SecurityRoleMapper;
import com.ynbwjf.myoauth.dao.SecurityUserToRoleMapper;
import com.ynbwjf.myoauth.entity.SecurityRole;
import com.ynbwjf.myoauth.entity.SecurityUserToRole;
import com.ynbwjf.myoauth.service.SecurityRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
@Service
public class SecurityRoleServiceImpl implements SecurityRoleService {
    @Autowired
    private SecurityRoleMapper securityRoleMapper;
    @Autowired
    private SecurityUserToRoleMapper securityUserToRoleMapper;

    @Override
    public List<SecurityRole> selectRolesByUserId(Long userId) {
        List<SecurityUserToRole> securityUserToRoles = securityUserToRoleMapper.selectUserToRoleByUserId(userId);
        List<SecurityRole> list = new ArrayList<>();
        if(!CollectionUtils.isEmpty(securityUserToRoles)){
            for (SecurityUserToRole securityUserToRole: securityUserToRoles) {
                SecurityRole securityRole = securityRoleMapper.selectByPrimaryKey(securityUserToRole.getId());
                list.add(securityRole);
            }
        }
        return list;
    }

    @Override
    public List<SecurityRole> selectAllRoles() {
        List<SecurityRole> securityRoles = securityRoleMapper.selectAllRoles();
        return securityRoles;
    }

    @Override
    public List<Long> selectRoleIdsByResourceId(Long resourceId) {
        List<Long> roleIds = securityRoleMapper.selectRoleIdsByResourceId(resourceId);
        return roleIds;
    }
}
