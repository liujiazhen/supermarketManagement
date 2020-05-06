package com.supermarket.management.service;

import com.supermarket.management.entity.Order;
import org.springframework.data.domain.Page;

public interface OrderService {

    Page<Order> getAllByPage(Order order);

    Order saveOrder(Order order);

    void deleteOrder(Long id);
}
