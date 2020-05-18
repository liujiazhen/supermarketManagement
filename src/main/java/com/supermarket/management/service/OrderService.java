package com.supermarket.management.service;

import com.supermarket.management.entity.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    Page<Order> getAllByPage(Order order);

    List<Order> getAllByPageGroupBy(Order order);

    Order saveOrder(Order order);

    void deleteOrder(Long id);

    int orderToInventory(Long id);
}
