package com.supermarket.management.service.impl;

import com.supermarket.management.dao.RolePermissionDao;
import com.supermarket.management.entity.RolePermission;
import com.supermarket.management.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public RolePermission save(RolePermission permission) {
        return rolePermissionDao.save(permission);
    }

    @Override
    public RolePermission getOne(Long id) {
        return rolePermissionDao.getDistinctByUserid(id);
    }
}
