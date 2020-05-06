package com.supermarket.management.service.impl;

import com.supermarket.management.dao.OrderDao;
import com.supermarket.management.entity.Order;
import com.supermarket.management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public Page<Order> getAllByPage(Order order) {
        Pageable pageable = PageRequest.of(order.getPage() - 1, order.getLimit());

        Page<Order> orderPage = orderDao.findAll((Specification<Order>) (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (order.getProductName() != null && !"".equals(order.getProductName())) {
                predicateList.add(cb.like(root.get("productName").as(String.class), order.getProductName() + "%"));
            }
            if (order.getProductCategory() != null && !"".equals(order.getProductCategory())) {
                predicateList.add(cb.like(root.get("productCategory").as(String.class), order.getProductCategory() + "%"));
            }
            if (order.getSupermarket() != null && !"".equals(order.getSupermarket())) {
                predicateList.add(cb.like(root.get("supermarket").as(String.class), order.getSupermarket() + "%"));
            }
            if (order.getCreateDate() != null && !"".equals(order.getCreateDate())) {
                Calendar cal = Calendar.getInstance();
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                Date date = cal.getTime();
                predicateList.add(cb.between(root.get("createDate").as(Date.class), date, new Date()));
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageable);
        return orderPage;
    }

    @Override
    public Order saveOrder(Order order) {
        Date date = new Date();
        order.setCreateDate(date);
        order.setUpdateDate(date);
        order.setInventoryFlag("0");
        return orderDao.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderDao.deleteById(id);
    }
}
