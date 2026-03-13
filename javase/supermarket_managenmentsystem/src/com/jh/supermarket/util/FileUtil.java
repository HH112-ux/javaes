package com.jh.supermarket.util;

import com.jh.supermarket.bean.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static final String DATA_DIR = "supermarket_data/";
    private static final String CASHIER_FILE = DATA_DIR + "cashier.dat";
    private static final String PRODUCT_FILE = DATA_DIR + "product.dat";
    private static final String TYPE_FILE = DATA_DIR + "type.dat";
    private static final String ORDER_FILE = DATA_DIR + "order.dat";
    private static final String LOG_FILE = DATA_DIR + "log.dat";
    private static final String ID_POOL_FILE = DATA_DIR + "id_pool.dat";


    private static final long serialVersionUID = 1L;

    static {
        File directory = new File(DATA_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static <T> void saveData(T data, String filePath) {
        File file = new File(filePath);
        File tempFile = new File(filePath + ".tmp");

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(tempFile))) {
            oos.writeObject(data);
            if (file.exists()) {
                file.delete();
            }
            tempFile.renameTo(file);
        } catch (IOException e) {
            System.err.println("【错误】数据保存失败：" + filePath);
            e.printStackTrace();
        }
    }

    public static <T> T loadData(String filePath) {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("【错误】数据加载失败：" + filePath);
            e.printStackTrace();
            return null;
        }
    }
    public static void saveCashiers(List<Cashier> list) {
        saveData(list, CASHIER_FILE);
    }

    public static List<Cashier> loadCashiers() {
        List<Cashier> list = loadData(CASHIER_FILE);
        return list == null ? new ArrayList<>() : list;
    }

    public static void saveProducts(List<Product> list) {
        saveData(list, PRODUCT_FILE);
    }

    public static List<Product> loadProducts() {
        List<Product> list = loadData(PRODUCT_FILE);
        return list == null ? new ArrayList<>() : list;
    }

    public static void saveTypes(List<Type> list) {
        saveData(list, TYPE_FILE);
    }

    public static List<Type> loadTypes() {
        List<Type> list = loadData(TYPE_FILE);
        return list == null ? new ArrayList<>() : list;
    }

    public static void saveOrders(List<Order> list) {
        saveData(list, ORDER_FILE);
    }

    public static List<Order> loadOrders() {
        List<Order> list = loadData(ORDER_FILE);
        return list == null ? new ArrayList<>() : list;
    }

    public static void saveLogs(List<Log> list) {
        saveData(list, LOG_FILE);
    }

    public static List<Log> loadLogs() {
        List<Log> list = loadData(LOG_FILE);
        return list == null ? new ArrayList<>() : list;
    }
    public static void saveIdPool() {
        IdUtil.IdPoolData data = new IdUtil.IdPoolData();
        data.cashierMaxId = IdUtil.CASHIER_MAX_ID;
        data.productMaxId = IdUtil.PRODUCT_MAX_ID;
        data.typeMaxId = IdUtil.TYPE_MAX_ID;
        data.orderMaxId = IdUtil.ORDER_MAX_ID;
        data.logMaxId = IdUtil.LOG_MAX_ID;
        data.cashierIdPool = IdUtil.CASHIER_ID_POOL;
        data.productIdPool = IdUtil.PRODUCT_ID_POOL;
        data.typeIdPool = IdUtil.TYPE_ID_POOL;
        data.orderIdPool = IdUtil.ORDER_ID_POOL;
        data.logIdPool = IdUtil.LOG_ID_POOL;
        saveData(data, ID_POOL_FILE);
    }

    public static void loadIdPool() {
        IdUtil.IdPoolData data = loadData(ID_POOL_FILE);
        if (data == null) return;
        IdUtil.CASHIER_MAX_ID = data.cashierMaxId;
        IdUtil.PRODUCT_MAX_ID = data.productMaxId;
        IdUtil.TYPE_MAX_ID = data.typeMaxId;
        IdUtil.ORDER_MAX_ID = data.orderMaxId;
        IdUtil.LOG_MAX_ID = data.logMaxId;
        if (data.cashierIdPool != null) IdUtil.CASHIER_ID_POOL.addAll(data.cashierIdPool);
        if (data.productIdPool != null) IdUtil.PRODUCT_ID_POOL.addAll(data.productIdPool);
        if (data.typeIdPool != null) IdUtil.TYPE_ID_POOL.addAll(data.typeIdPool);
        if (data.orderIdPool != null) IdUtil.ORDER_ID_POOL.addAll(data.orderIdPool);
        if (data.logIdPool != null) IdUtil.LOG_ID_POOL.addAll(data.logIdPool);
    }

    public static void saveAllData() {
        saveCashiers(DataUtil.cashiersData);
        saveProducts(DataUtil.productsData);
        saveTypes(DataUtil.typeData);
        saveOrders(DataUtil.ordersData);
        saveLogs(DataUtil.logsData);
        saveIdPool();
        System.out.println("所有数据已通过文件流持久化到本地！");
    }

}
