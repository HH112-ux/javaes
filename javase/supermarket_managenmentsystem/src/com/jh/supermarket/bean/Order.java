package com.jh.supermarket.bean;

import com.jh.supermarket.util.IdUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String cashierID;
    private Map<String, Integer> product;
    private BigDecimal totalPrice;
    private Date date;

    public Order() {
        this.date = new Date();
        this.product = new HashMap<>();
        this.id = IdUtil.getOrderId();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCashierID() {
        return cashierID;
    }

    public void setCashierID(String cashierID) {
        this.cashierID = cashierID;
    }

    public Map<String, Integer> getProduct() {
        return product;
    }

    public void setProduct(Map<String, Integer> product) {
        this.product = product;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
