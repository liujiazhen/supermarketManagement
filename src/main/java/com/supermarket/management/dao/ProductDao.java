package com.supermarket.management.dao;

import com.supermarket.management.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> , JpaSpecificationExecutor<Product> {
    @Query(value = "select id, name from t_product", nativeQuery = true)
    List<Map<String, String>> getAllProductIdAndName();

    @Query(value = "select * from t_product where id = ?1", nativeQuery = true)
    Product getProductById(Long id);
}
