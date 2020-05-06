package com.supermarket.management.controller;

import com.supermarket.management.entity.Product;
import com.supermarket.management.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping("/getProductList")
    public Map<String, Object> getProductList(@RequestBody Product product) {
        Page<Product> productList = productService.findAll(product);

        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        resultMap.put("data", productList.getContent());
        resultMap.put("count", productList.getTotalElements());
        return resultMap;
    }

    @RequestMapping("/addProduct")
    public int saveProduct(Product product) {
        productService.addProduct(product);
        return 1;
    }

    @RequestMapping("/getAllProductIdAndName")
    public Map<String, Object> getAllProductIdAndName() {
        List<Map<String, String>> mapList = productService.getAllProductIdAndName();

        Map<String, Object> resultMap = new HashMap<>(4);
        resultMap.put("code", "0");
        resultMap.put("msg", "");
        resultMap.put("data", mapList);
        resultMap.put("count", mapList.size());
        return resultMap;
    }

    @RequestMapping("/getProductById/{id}")
    public Product getProductById(@PathVariable Long id) {
        Product productById = productService.getProductById(id);
        return productById;
    }

    @RequestMapping("/deleteProduct")
    public int deleteProduct(Long id) {
        productService.deleteProduct(id);
        return 1;
    }
}
