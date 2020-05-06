package com.supermarket.management.service;

import com.supermarket.management.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface ProductService {
    Page<Product> findAll(Product product);

    Product addProduct(Product product);

    List<Map<String, String>> getAllProductIdAndName();

    Product getProductById(Long id);

    void deleteProduct(Long id);
}
