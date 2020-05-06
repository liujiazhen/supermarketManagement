package com.supermarket.management.service.impl;

import com.supermarket.management.dao.RoleDao;
import com.supermarket.management.entity.Role;
import com.supermarket.management.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<Role> getAll() {
        return roleDao.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleDao.getRoleById(id);
    }
}
