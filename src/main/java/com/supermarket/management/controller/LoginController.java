package com.supermarket.management.controller;

import com.supermarket.management.config.security.SecurityUserDetails;
import com.supermarket.management.entity.User;
import com.supermarket.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

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
}
