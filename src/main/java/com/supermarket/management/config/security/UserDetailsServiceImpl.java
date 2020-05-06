package com.supermarket.management.config.security;

import com.supermarket.management.entity.Role;
import com.supermarket.management.entity.RolePermission;
import com.supermarket.management.entity.User;
import com.supermarket.management.service.RolePermissionService;
import com.supermarket.management.service.RoleService;
import com.supermarket.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    private RoleService roleService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(s);
        if (user == null) {
            throw new UsernameNotFoundException("Username " + s + " not found");
        }
        RolePermission rolePermission = rolePermissionService.getOne(user.getId());
        Role role = roleService.getRoleById(rolePermission.getRoleid());
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        return new SecurityUserDetails(user);
    }
}
