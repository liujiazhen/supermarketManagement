package com.supermarket.management.controller;

import com.supermarket.management.config.security.SecurityUserDetails;
import com.supermarket.management.entity.Inventory;
import com.supermarket.management.entity.User;
import com.supermarket.management.service.UserService;
import com.supermarket.management.service.impl.InventoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class InventoryController {
    private static final Logger LOG = LoggerFactory.getLogger(InventoryController.class);
    private final InventoryServiceImpl inventoryService;
    private final UserService userService;

    public InventoryController(InventoryServiceImpl inventoryService, UserService userService) {
        this.inventoryService = inventoryService;
        this.userService = userService;
    }

    @RequestMapping("/getInventoryList")
    public Map<String, Object> getProductList(@RequestBody Inventory inventory) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUserDetails securityUserDetails = (SecurityUserDetails) authentication.getPrincipal();
        User user = userService.getUserByUserName(securityUserDetails.getUsername());
        LOG.info(user.getUsername() + "获取库存列表！");
        if (!"admin".equals(user.getUsername()) && !"buyer".equals(user.getUsername())) {
            inventory.setSupermarket(user.getSupermarket());
        }
        Page<Inventory> orderPage = inventoryService.getAllByPage(inventory);

        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("code", "0");
        resultMap.put("msg", "成功");
        resultMap.put("data", orderPage.getContent());
        resultMap.put("count", orderPage.getTotalElements());
        return resultMap;
    }

    @RequestMapping("/updateInventory")
    public int updateInventory(Long id, int qty) {
        int x = inventoryService.updateInventory(id, qty);

        return 1;
    }

}
