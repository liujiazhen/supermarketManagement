package com.supermarket.management.service;

import com.supermarket.management.entity.Role;

import java.util.List;

public interface RoleService {

    public Role getRoleById(Long id);

    public List<Role> getAll();
}
