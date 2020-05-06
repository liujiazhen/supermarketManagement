package com.supermarket.management.dao;

import com.supermarket.management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryDao extends JpaRepository<Product, Long> {
    List<Product> getAllByName(String name);
}
