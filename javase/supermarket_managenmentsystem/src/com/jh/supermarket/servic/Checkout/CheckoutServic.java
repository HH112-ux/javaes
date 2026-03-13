package com.jh.supermarket.servic.Checkout;

import com.jh.supermarket.bean.Cashier;
import com.jh.supermarket.bean.Order;
import com.jh.supermarket.bean.Product;
import com.jh.supermarket.enums.LogTypeEnum;
import com.jh.supermarket.util.FileUtil;
import com.jh.supermarket.util.IdUtil;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.jh.supermarket.util.DataUtil.*;

public class CheckoutServic {
    private static Scanner sc = new Scanner(System.in);
    private static Cashier currentCashier;

    public void loginCheckout() {
        System.out.println("-----------收银员登录----------");
        if (cashiersData.isEmpty()) {
            System.out.println("暂无收银员信息，请先添加收银员");
            addLog("登录", "收银员登录失败，暂无收银员信息", false, LogTypeEnum.CASHIER_LOG);
            return;
        }

        System.out.println("请输入账号：");
        String account = sc.next();
        System.out.println("请输入密码：");
        String password = sc.next();

        Cashier cashier = getCashierByAccount(account);
        if (cashier == null) {
            System.out.println("账号不存在，登录失败");
            addLog("登录", "收银员登录失败，账号不存在：" + account, false, LogTypeEnum.CASHIER_LOG);
            return;
        }

        if (!cashier.getPassword().equals(password)) {
            System.out.println("密码错误，登录失败");
            addLog("登录", "收银员登录失败，密码错误，账号：" + account, false, LogTypeEnum.CASHIER_LOG);
            return;
        }

        currentCashier = cashier;
        System.out.println("登录成功！欢迎，" + cashier.getName());
        addLog("登录", "收银员登录成功，姓名：" + cashier.getName(), true, LogTypeEnum.CASHIER_LOG);
    }

    public void logoutCheckout() {
        if (currentCashier != null) {
            System.out.println("收银员 " + currentCashier.getName() + " 已登出");
            addLog("登出", "收银员登出成功，姓名：" + currentCashier.getName(), true, LogTypeEnum.CASHIER_LOG);
            currentCashier = null;
        }
    }

    public boolean isCashierLoggedIn() {
        return currentCashier != null;
    }

    public void addOrder() {
        if (!isCashierLoggedIn()) {
            System.out.println("请先登录收银系统！");
            return;
        }

        System.out.println("-----------新增订单----------");
        if (productsData.isEmpty()) {
            System.out.println("暂无商品信息，无法创建订单");
            addLog("新增", "新增订单失败，暂无商品信息", false, LogTypeEnum.CASH_LOG);
            return;
        }

        Order order = new Order();
        order.setCashierID(currentCashier.getId()); // 使用当前登录的收银员ID
        Map<String, Integer> orderItems = new HashMap<>();
        BigDecimal totalPrice = BigDecimal.ZERO;

        System.out.println("当前商品列表：");
        showProductList();

        while (true) {
            System.out.println("请输入商品ID（输入'done'结束添加）：");
            String productId = sc.next();
            if ("done".equalsIgnoreCase(productId)) {
                break;
            }

            Product product = getProductById(productId);
            if (product == null) {
                System.out.println("商品ID不存在，请重新输入");
                continue;
            }

            System.out.println("当前商品库存：" + product.getCount() + "个");
            int quantity;
            while (true) {
                System.out.println("请输入购买数量：");
                try {
                    quantity = sc.nextInt();
                    if (quantity <= 0) {
                        System.out.println("购买数量必须大于0");
                        continue;
                    }
                    if (quantity > product.getCount()) {
                        System.out.println("购买数量不能超过库存：" + product.getCount());
                        continue;
                    }
                    break;
                } catch (Exception e) {
                    System.out.println("请输入有效的数字");
                    sc.nextLine(); // 清除错误输入
                }
            }

            orderItems.put(productId, quantity);
            BigDecimal itemTotal = product.getPrice().multiply(new BigDecimal(quantity));
            totalPrice = totalPrice.add(itemTotal);
            product.setCount(product.getCount() - quantity); // 减少库存
        }

        if (orderItems.isEmpty()) {
            System.out.println("未添加任何商品，订单创建失败");
            addLog("新增", "新增订单失败，未添加任何商品", false, LogTypeEnum.CASH_LOG);
            return;
        }

        order.getProduct().putAll(orderItems);
        order.setTotalPrice(totalPrice);
        ordersData.add(order);

        System.out.println("订单创建成功！");
        System.out.println("订单ID：" + order.getId());
        System.out.println("收银员：" + currentCashier.getName());
        System.out.println("总金额：" + totalPrice + "元");
        System.out.println("商品详情：");
        for (Map.Entry<String, Integer> entry : orderItems.entrySet()) {
            Product p = getProductById(entry.getKey());
            System.out.println("  " + p.getName() + " x" + entry.getValue() + " = " +
                    p.getPrice().multiply(new BigDecimal(entry.getValue())) + "元");
        }

        FileUtil.saveProducts(productsData);
        FileUtil.saveOrders(ordersData);
        addLog("新增", "新增订单成功，订单ID：" + order.getId() + "，金额：" + totalPrice, true, LogTypeEnum.CASH_LOG);
    }

    private void showProductList() {
        System.out.printf("%-10s %-15s %-10s %-10s%n", "编号", "名称", "价格", "数量");
        for (Product p : productsData) {
            System.out.printf("%-10s %-15s %-10s %-10s%n",
                    p.getId(), p.getName(), p.getPrice(), p.getCount());
        }
    }

    public void showOrderList() {
        if (!isCashierLoggedIn()) {
            System.out.println("请先登录收银系统！");
            return;
        }

        System.out.println("-----------订单列表----------");
        if (ordersData.isEmpty()) {
            System.out.println("暂无订单信息");
            addLog("查看", "查看订单列表失败，暂无订单信息", false, LogTypeEnum.CASH_LOG);
            return;
        }

        System.out.printf("%-10s %-10s %-15s %-10s %-15s%n", "订单ID", "收银员ID", "日期", "总金额", "商品数量");
        for (Order o : ordersData) {
            int itemCount = o.getProduct().values().stream().mapToInt(Integer::intValue).sum();
            System.out.printf("%-10s %-10s %-15s %-10s %-15s%n",
                    o.getId(), o.getCashierID(), o.getDate(), o.getTotalPrice(), itemCount);
        }
        System.out.println("-----------订单列表----------");
        System.out.println("共计" + ordersData.size() + "条订单");
        addLog("查看", "查看订单列表成功，共" + ordersData.size() + "条订单", true, LogTypeEnum.CASH_LOG);
    }

    public void findOrder() {
        if (!isCashierLoggedIn()) {
            System.out.println("请先登录收银系统！");
            return;
        }

        System.out.println("-----------查找订单----------");
        if (ordersData.isEmpty()) {
            System.out.println("暂无订单信息");
            addLog("查找", "查找订单失败，暂无订单信息", false, LogTypeEnum.CASH_LOG);
            return;
        }

        System.out.println("请输入要查找的订单ID：");
        String orderId = sc.next();
        Order order = getOrderById(orderId);
        if (order == null) {
            System.out.println("订单ID不存在");
            addLog("查找", "查找订单失败，订单ID：" + orderId + "不存在", false, LogTypeEnum.CASH_LOG);
            return;
        }

        System.out.println("找到订单信息：");
        System.out.println("订单ID：" + order.getId());
        System.out.println("收银员ID：" + order.getCashierID());
        System.out.println("日期：" + order.getDate());
        System.out.println("总金额：" + order.getTotalPrice() + "元");
        System.out.println("商品详情：");
        for (Map.Entry<String, Integer> entry : order.getProduct().entrySet()) {
            Product p = getProductById(entry.getKey());
            if (p != null) {
                System.out.println("  " + p.getName() + " x" + entry.getValue() + " = " +
                        p.getPrice().multiply(new BigDecimal(entry.getValue())) + "元");
            }
        }
        addLog("查找", "查找订单成功，订单ID：" + orderId, true, LogTypeEnum.CASH_LOG);
    }

    public void delOrder() {
        if (!isCashierLoggedIn()) {
            System.out.println("请先登录收银系统！");
            return;
        }

        System.out.println("-----------删除订单----------");
        showOrderList();
        if (ordersData.isEmpty()) {
            System.out.println("暂无订单信息");
            addLog("删除", "删除订单失败，暂无订单信息", false, LogTypeEnum.CASH_LOG);
            return;
        }

        System.out.println("请输入要删除的订单ID：");
        String orderId = sc.next();
        Order order = getOrderById(orderId);
        if (order == null) {
            System.out.println("订单ID不存在");
            addLog("删除", "删除订单失败，订单ID：" + orderId + "不存在", false, LogTypeEnum.CASH_LOG);
            return;
        }
        for (Map.Entry<String, Integer> entry : order.getProduct().entrySet()) {
            Product product = getProductById(entry.getKey());
            if (product != null) {
                product.setCount(product.getCount() + entry.getValue());
            }
        }

        ordersData.remove(order);
        IdUtil.recycleOrderId(orderId);
        FileUtil.saveProducts(productsData);
        FileUtil.saveOrders(ordersData);

        System.out.println("订单删除成功！订单ID：" + orderId);
        addLog("删除", "删除订单成功，订单ID：" + orderId, true, LogTypeEnum.CASH_LOG);
    }
}