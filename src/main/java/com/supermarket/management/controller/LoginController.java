package com.supermarket.management.controller;

import com.supermarket.management.config.security.SecurityUserDetails;
import com.supermarket.management.entity.Order;
import com.supermarket.management.entity.RolePermission;
import com.supermarket.management.entity.User;
import com.supermarket.management.service.RolePermissionService;
import com.supermarket.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private RolePermissionService rolePermissionService;
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/getCurrentUser")
    @ResponseBody
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();
        User userByUserName = userService.getUserByUserName(securityUserDetails.getUsername());
        securityUserDetails.setSupermarket(userByUserName.getSupermarket());
        return securityUserDetails;
    }

    @RequestMapping("/getUserList")
    @ResponseBody
    public Map<String, Object> getUserList(@RequestBody User user) {

        Page<User> userPage = userService.getAllByPage(user);

        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        resultMap.put("data", userPage.getContent());
        resultMap.put("count", userPage.getTotalElements());
        return resultMap;
    }

    @RequestMapping("/saveUser")
    @ResponseBody
    public int saveOrder(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User user1 = userService.insertUser(user);
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleid(3L);
        rolePermission.setUserid(user1.getId());
        rolePermissionService.save(rolePermission);
        return 1;
    }

    @RequestMapping("/updateUserPassword")
    @ResponseBody
    public int updateUserPassword(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.updateUserPassword(user.getId(),user.getPassword());
        return 1;
    }


    @RequestMapping("/deleteSupermarket")
    @ResponseBody
    public int deleteSupermarket(Long id) {
        userService.deleteSupermarket(id);
        return 1;
    }
}
