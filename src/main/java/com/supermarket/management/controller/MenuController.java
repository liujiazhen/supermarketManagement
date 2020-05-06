package com.supermarket.management.controller;

import com.supermarket.management.config.security.SecurityUserDetails;
import com.supermarket.management.entity.Menus;
import com.supermarket.management.entity.Role;
import com.supermarket.management.entity.RoleMenus;
import com.supermarket.management.service.MenuService;
import com.supermarket.management.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class MenuController {

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private MenuService menuService;

    /**
     * 通过角色id获取菜单列表
     *
     * @param roleid
     * @return
     */
    @RequestMapping("/getMenuList")
    public List<Menus> getMenuList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();
        List<Role> roles = securityUserDetails.getRoles();
        List<RoleMenus> roleMenus = roleMenuService.getRoleMenuList(roles.get(0).getId());
        List<Menus> menusList = new ArrayList<>();
        roleMenus.forEach(rm -> {
            menusList.add(menuService.getMenus(rm.getMenuid()));
        });
        return menusList;
    }
}
