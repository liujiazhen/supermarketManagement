package com.supermarket.management.service;

import com.supermarket.management.entity.RolePermission;

public interface RolePermissionService {

    RolePermission getOne(Long id);

    RolePermission save(RolePermission permission);
}
