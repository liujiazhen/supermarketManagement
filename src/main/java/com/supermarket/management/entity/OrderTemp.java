package com.supermarket.management.entity;

import java.io.Serializable;

public class OrderTemp implements Serializable {
    public OrderTemp() {
    }

    public OrderTemp(Long productId, String productName, Long qty, String productCategory, String productUnit) {
        this.productId = productId;
        this.productName = productName;
        this.qty = qty;
        this.productCategory = productCategory;
        this.productUnit = productUnit;
    }

    private Long productId;

    private String productName;

    private Long qty;

    private String productCategory;

    private String productUnit;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductUnit() {
        return productUnit;
    }

    public void setProductUnit(String productUnit) {
        this.productUnit = productUnit;
    }

    @Override
    public String toString() {
        return "OrderTemp{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", qty=" + qty +
                ", productCategory='" + productCategory + '\'' +
                ", productUnit='" + productUnit + '\'' +
                '}';
    }
}
