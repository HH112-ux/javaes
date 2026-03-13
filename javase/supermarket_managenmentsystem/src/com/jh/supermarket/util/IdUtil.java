package com.jh.supermarket.util;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

public class IdUtil {

    public static final Queue<String> CASHIER_ID_POOL = new LinkedList<>();
    public static final Queue<String> PRODUCT_ID_POOL = new LinkedList<>();
    public static final Queue<String> TYPE_ID_POOL = new LinkedList<>();
    public static final Queue<String> ORDER_ID_POOL = new LinkedList<>();
    public static final Queue<String> LOG_ID_POOL = new LinkedList<>();

    public static int CASHIER_MAX_ID = 0;
    public static int PRODUCT_MAX_ID = 0;
    public static int TYPE_MAX_ID = 0;
    public static int ORDER_MAX_ID = 0;
    public static int LOG_MAX_ID = 0;

    public static void setCashierMaxId(int cashierMaxId) {
        CASHIER_MAX_ID = cashierMaxId;
    }

    public static void setProductMaxId(int productMaxId) {
        PRODUCT_MAX_ID = productMaxId;
    }

    public static void setTypeMaxId(int typeMaxId) {
        TYPE_MAX_ID = typeMaxId;
    }

    public static void setOrderMaxId(int orderMaxId) {
        ORDER_MAX_ID = orderMaxId;
    }

    public static void setLogMaxId(int logMaxId) {
        LOG_MAX_ID = logMaxId;
    }

    public static String getCashierId() {
        return CASHIER_ID_POOL.isEmpty() ? "C-" + (++CASHIER_MAX_ID) : CASHIER_ID_POOL.poll();
    }

    public static String getProductId() {
        return PRODUCT_ID_POOL.isEmpty() ? "P-" + (++PRODUCT_MAX_ID) : PRODUCT_ID_POOL.poll();
    }

    public static String getTypeId() {
        return TYPE_ID_POOL.isEmpty() ? "T-" + (++TYPE_MAX_ID) : TYPE_ID_POOL.poll();
    }

    public static String getOrderId() {
        return ORDER_ID_POOL.isEmpty() ? "O-" + (++ORDER_MAX_ID) : ORDER_ID_POOL.poll();
    }

    public static String getLogId() {
        return LOG_ID_POOL.isEmpty() ? "L-" + (++LOG_MAX_ID) : LOG_ID_POOL.poll();
    }

    public static void recycleCashierId(String id) {
        CASHIER_ID_POOL.offer(id);
    }

    public static void recycleProductId(String id) {
        PRODUCT_ID_POOL.offer(id);
    }

    public static void recycleTypeId(String id) {
        TYPE_ID_POOL.offer(id);
    }

    public static void recycleOrderId(String id) {
        ORDER_ID_POOL.offer(id);
    }

    public static void recycleLogId(String id) {
        LOG_ID_POOL.offer(id);
    }

    public static void resetMaxId(String type, int maxId) {
        switch (type) {
            case "cashier": CASHIER_MAX_ID = maxId; break;
            case "product": PRODUCT_MAX_ID = maxId; break;
            case "type": TYPE_MAX_ID = maxId; break;
            case "order": ORDER_MAX_ID = maxId; break;
            case "log": LOG_MAX_ID = maxId; break;
        }
    }

    public static class IdPoolData implements Serializable {
        private static final long serialVersionUID = 1L;
        public int cashierMaxId;
        public int productMaxId;
        public int typeMaxId;
        public int orderMaxId;
        public int logMaxId;
        public Queue<String> cashierIdPool;
        public Queue<String> productIdPool;
        public Queue<String> typeIdPool;
        public Queue<String> orderIdPool;
        public Queue<String> logIdPool;
    }
}