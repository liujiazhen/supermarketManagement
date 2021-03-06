package com.supermarket.management.service.impl;

import com.supermarket.management.dao.InventoryDao;
import com.supermarket.management.dao.OrderDao;
import com.supermarket.management.entity.Inventory;
import com.supermarket.management.entity.Order;
import com.supermarket.management.entity.OrderTemp;
import com.supermarket.management.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
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
            if (order.getDateStart()!=null&&!"".equals(order.getDateStart())&&order.getDateEnd()!=null&&!"".equals(order.getDateEnd())){
                predicateList.add(cb.between(root.get("createDate").as(Date.class),order.getDateStart(),order.getDateEnd()));
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageable);
        return orderPage;
    }

    @Override
    public List<Order> getAllByPageGroupBy(Order order) {
        List<Object> all = orderDao.getAll(order.getSupermarket(), (order.getPage() -1) * order.getLimit(), order.getLimit());
        List<Order> orders = new ArrayList<>();
        for (int i = 0; i < all.size(); i++) {
            Order temp = new Order();
            Object[] oo = (Object[])all.get(i);
            BigInteger b = (BigInteger) oo[0];
            temp.setProductId(b.longValue());
            temp.setProductName((String)oo[1]);
            temp.setProductCategory((String)oo[2]);
            temp.setProductUnit((String)oo[3]);
            BigDecimal b2 = (BigDecimal) oo[4];
            temp.setQty(b2.longValue());
            orders.add(temp);
        }
        return orders;
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

    @Autowired
    private InventoryDao inventoryDao;

    @Override
    public int orderToInventory(Long id) {
        Order order = orderDao.getOne(id);
        if ("1".equals(order.getInventoryFlag())) {
            return 1;
        }
        int x = orderDao.updateInventory(id);
        Inventory inventory = inventoryDao.getInventoryByProductIdAndProductCategoryAndSupermarket(order.getProductId(), order.getProductCategory(), order.getSupermarket());
        if (inventory != null) {
            inventoryDao.addInventory(inventory.getId(), order.getQty());
        } else {
            Inventory temp = new Inventory();
            temp.setProductId(order.getProductId());
            temp.setProductName(order.getProductName());
            temp.setProductUnit(order.getProductUnit());
            temp.setProductCategory(order.getProductCategory());
            temp.setQty(order.getQty());
            temp.setSupermarket(order.getSupermarket());
            temp.setCreateDate(new Date());
            inventoryDao.save(temp);
        }
        return 0;
    }
}
