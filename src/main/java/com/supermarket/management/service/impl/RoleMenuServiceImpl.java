package com.supermarket.management.service.impl;

import com.supermarket.management.dao.RoleMenusDao;
import com.supermarket.management.entity.RoleMenus;
import com.supermarket.management.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenusDao roleMenusDao;

    @Override
    public List<RoleMenus> getRoleMenuList(Long roleid) {
        return roleMenusDao.getRoleMenusByRoleid(roleid);
    }
}
