package com.supermarket.management.service.impl;

import com.supermarket.management.dao.ProductDao;
import com.supermarket.management.entity.Product;
import com.supermarket.management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Page<Product> findAll(Product product) {
        Pageable pageable = PageRequest.of(product.getPage() - 1, product.getLimit());
        Page<Product> products = productDao.findAll((Specification<Product>) (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            if (product.getName() != null && !"".equals(product.getName())) {
                predicateList.add(cb.like(root.get("name").as(String.class), product.getName() + "%"));
            }
            if (product.getCategory() != null && !"".equals(product.getCategory())) {
                predicateList.add(cb.like(root.get("category").as(String.class), product.getCategory() + "%"));
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        }, pageable);
        return products;
    }

    @Override
    public Product addProduct(Product product) {
        Date date = new Date();
        product.setCreateDate(date);
        product.setUpdateDate(date);
        return productDao.save(product);
    }

    @Override
    public List<Map<String, String>> getAllProductIdAndName() {
        return productDao.getAllProductIdAndName();
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productDao.getProductById(id);
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        productDao.deleteById(id);
    }

}
