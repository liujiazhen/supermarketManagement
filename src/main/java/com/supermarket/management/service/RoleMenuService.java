package com.supermarket.management.service;

import com.supermarket.management.entity.RoleMenus;

import java.util.List;

public interface RoleMenuService {

    List<RoleMenus> getRoleMenuList(Long roleid);
}
