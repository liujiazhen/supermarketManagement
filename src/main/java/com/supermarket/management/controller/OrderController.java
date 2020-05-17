package com.supermarket.management.controller;

import com.supermarket.management.config.security.SecurityUserDetails;
import com.supermarket.management.entity.Order;
import com.supermarket.management.entity.User;
import com.supermarket.management.service.OrderService;
import com.supermarket.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @RequestMapping("/getOrderList")
    public Map<String, Object> getProductList(@RequestBody Order order) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();
        User user = userService.getUserByUserName(securityUserDetails.getUsername());
        if (!"admin".equals(user.getUsername()) && !"buyer".equals(user.getUsername())) {
            order.setSupermarket(user.getSupermarket());
        }
        Page<Order> orderPage = orderService.getAllByPage(order);

        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        resultMap.put("data", orderPage.getContent());
        resultMap.put("count", orderPage.getTotalElements());
        return resultMap;
    }

    @RequestMapping("/getOrderList2")
    public Map<String, Object> getProductList2(@RequestBody Order order) {
        Page<Order> orderPage = orderService.getAllByPage2(order);

        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        resultMap.put("data", orderPage.getContent());
        resultMap.put("count", orderPage.getTotalElements());
        return resultMap;
    }

    @RequestMapping("/saveOrder")
    public int saveOrder(Order order) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();
        User user = userService.getUserByUserName(securityUserDetails.getUsername());
        order.setSupermarket(user.getSupermarket());
        orderService.saveOrder(order);
        return 1;
    }

    @RequestMapping("/orderDelete")
    public int orderDelete(Long id) {
        orderService.deleteOrder(id);
        return 1;
    }

    @RequestMapping("/getAllSupermarket")
    public Map<String, Object> getAllSupermarket(Long id) {
        List<String> allSupermarket = userService.getAllSupermarket();
        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        resultMap.put("data", allSupermarket.size());
        resultMap.put("count", allSupermarket);
        return resultMap;
    }

    @RequestMapping("/orderToInventory")
    public int orderToInventory(Long id) {
        orderService.orderToInventory(id);
        return 1;
    }

}
