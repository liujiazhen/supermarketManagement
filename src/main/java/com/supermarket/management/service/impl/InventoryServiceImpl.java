package com.supermarket.management.service.impl;

import com.supermarket.management.dao.InventoryDao;
import com.supermarket.management.entity.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class InventoryServiceImpl {
    @Autowired
    private InventoryDao inventoryDao;

    public Page<Inventory> getAllByPage(Inventory inventory) {
        Pageable pageable = PageRequest.of(inventory.getPage() - 1, inventory.getLimit());

        Page<Inventory> orderPage = inventoryDao.findAll((Specification<Inventory>) (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (inventory.getProductName() != null && !"".equals(inventory.getProductName())) {
                predicateList.add(cb.like(root.get("productName").as(String.class), inventory.getProductName() + "%"));
            }
            if (inventory.getProductCategory() != null && !"".equals(inventory.getProductCategory())) {
                predicateList.add(cb.like(root.get("productCategory").as(String.class), inventory.getProductCategory() + "%"));
            }
            if (inventory.getSupermarket() != null && !"".equals(inventory.getSupermarket())) {
                predicateList.add(cb.like(root.get("supermarket").as(String.class), inventory.getSupermarket() + "%"));
            }
            if (inventory.getCreateDate() != null && !"".equals(inventory.getCreateDate())) {
                Calendar cal = Calendar.getInstance();
                cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
                Date date = cal.getTime();
                predicateList.add(cb.between(root.get("createDate").as(Date.class), date, new Date()));
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageable);
        return orderPage;
    }

    public int updateInventory(Long inventory, int qty) {
        inventoryDao.updateInventory(inventory, qty);
        return 1;
    }
}
