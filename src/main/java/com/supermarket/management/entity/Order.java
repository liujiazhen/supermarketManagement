package com.supermarket.management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long productId;

    private String productName;

    private Long qty;

    private String productCategory;

    private String productUnit;

    private String userId;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateDate;

    private String supermarket;

    private String inventoryFlag;

    @Transient
    private int page;
    @Transient
    private int limit;
    @Transient
    private Date dateStart;
    @Transient
    private Date dateEnd;

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getInventoryFlag() {
        return inventoryFlag;
    }

    public void setInventoryFlag(String inventoryFlag) {
        this.inventoryFlag = inventoryFlag;
    }

    public String getSupermarket() {
        return supermarket;
    }

    public void setSupermarket(String supermarket) {
        this.supermarket = supermarket;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getQty() {
        return qty;
    }

    public void setQty(Long qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", qty=" + qty +
                ", productCategory='" + productCategory + '\'' +
                ", productUnit='" + productUnit + '\'' +
                ", userId='" + userId + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", supermarket='" + supermarket + '\'' +
                ", inventoryFlag='" + inventoryFlag + '\'' +
                ", page=" + page +
                ", limit=" + limit +
                '}';
    }
}
