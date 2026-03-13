package com.jh.supermarket.util;

import com.jh.supermarket.bean.*;
import com.jh.supermarket.enums.LogTypeEnum;

import java.util.*;

public class DataUtil {
    public static List<Cashier> cashiersData = new ArrayList<>();
    public static List<Log> logsData = new ArrayList<>();
    public static List<Product> productsData = new ArrayList<>();
    public static List<Order> ordersData = new ArrayList<>();
    public static List<Type> typeData = new ArrayList<>();

    public static Cashier getCashierById(String id) {
        return cashiersData.stream().filter(c -> id.equals(c.getId())).findFirst().orElse(null);
    }

    public static Cashier getCashierByAccount(String account) {
        return cashiersData.stream().filter(c -> account.equals(c.getAccount())).findFirst().orElse(null);
    }

    public static void addLog(String title, String description, boolean success, LogTypeEnum logType) {
        Log log = new Log();
        log.setTitle(title);
        log.setDescription(description);
        log.setSuccess(success);
        log.setLogType(logType.getCode());
        logsData.add(log);
    }

    public static Product getProductById(String id) {
        return productsData.stream().filter(p -> id.equals(p.getId())).findFirst().orElse(null);
    }

    public static Product getProductByname(String name) {
        return productsData.stream().filter(p -> name.equals(p.getName())).findFirst().orElse(null);
    }

    public static Type getTypeById(String id) {
        return typeData.stream().filter(t -> id.equals(t.getId())).findFirst().orElse(null);
    }

    public static Type getTypeByname(String name) {
        return typeData.stream().filter(t -> name.equals(t.getName())).findFirst().orElse(null);
    }

    public static Order getOrderById(String id) {
        return ordersData.stream().filter(o -> id.equals(o.getId())).findFirst().orElse(null);
    }

    public static void initMaxId() {
        IdUtil.resetMaxId("cashier", cashiersData.stream().mapToInt(c -> Integer.parseInt(c.getId())).max().orElse(0));
        IdUtil.resetMaxId("product", productsData.stream().mapToInt(p -> Integer.parseInt(p.getId())).max().orElse(0));
        IdUtil.resetMaxId("type", typeData.stream().mapToInt(t -> Integer.parseInt(t.getId())).max().orElse(0));
        IdUtil.resetMaxId("order", ordersData.stream().mapToInt(o -> Integer.parseInt(o.getId())).max().orElse(0));
        IdUtil.resetMaxId("log", logsData.stream().mapToInt(l -> Integer.parseInt(l.getId())).max().orElse(0));
    }

    public static Map<String,Object> totalStock() {
        Map<String, Object> stock = new HashMap<>();
        int count = productsData.stream().mapToInt(Product::getCount).sum();
        double totalAmount = productsData.stream().mapToDouble(p -> p.getPrice().doubleValue() * p.getCount()).sum();
        double avgPrice = productsData.isEmpty() ? 0 : totalAmount / count;
        stock.put("totalCount", count);
        stock.put("totalAmount", String.format("%.2f", totalAmount));
        stock.put("avgPrice", String.format("%.2f", avgPrice));
        return stock;
    }

    public static Map<String, Object> getSaleStat() {
        Map<String, Object> stat = new HashMap<>();
        int totalSaleCount = 0;
        double totalSaleAmount = 0;
        for (Order o : ordersData) {
            for (Map.Entry<String, Integer> entry : o.getProduct().entrySet()) {
                Product p = getProductById(entry.getKey());
                if (p != null) {
                    totalSaleCount += entry.getValue();
                    totalSaleAmount += p.getPrice().doubleValue() * entry.getValue();
                }
            }
        }
        double avgSalePrice = totalSaleCount == 0 ? 0 : totalSaleAmount / totalSaleCount;
        stat.put("totalSaleCount", totalSaleCount);
        stat.put("totalSaleAmount", String.format("%.2f", totalSaleAmount));
        stat.put("avgSalePrice", String.format("%.2f", avgSalePrice));
        return stat;
    }
}
