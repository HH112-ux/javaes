package com.simulation;

import java.util.Map;
import java.util.Queue;

/**
 * @author jh
 * @project com.simulation
 * @time 2026/1/22
 */
public class Simulation {
    private static final String[] STATUS_FLOW = {
            "货品已出库",
            "上门收取快递",
            "送到快递分包中心",
            "货物运输途中",
            "货物已由快递员送货到小区分配中心",
            "货物已被取走"
    };

    protected static void initOrders(Queue<String> orderQueue, Map<String, Integer> orderStatusMap) {
        orderQueue.add("ORDER_001");
        orderQueue.add("ORDER_002");
        orderStatusMap.put("ORDER_001", 0);
        orderStatusMap.put("ORDER_002", 0);
    }

    protected static void processAllOrders(Queue<String> orderQueue, Map<String, Integer> orderStatusMap) {
        while (!orderQueue.isEmpty()) {
            String id = orderQueue.poll();
            int currentStatus = orderStatusMap.get(id);
            System.out.println("处理订单：" + id + "，当前状态：" + STATUS_FLOW[currentStatus]);
            currentStatus++;
            if (currentStatus < STATUS_FLOW.length) {
                orderStatusMap.put(id, currentStatus);
                orderQueue.add(id);
            } else {
                System.out.println("订单" + id + " 已完成全部流程");
                orderStatusMap.remove(id);
            }
        }
    }

    protected static void queryOrderStatus(String id, Map<String, Integer> orderStatusMap) {
        if (orderStatusMap.containsKey(id)) {
            int statusIndex = orderStatusMap.get(id);
            System.out.println("\n订单 " + id + " 当前状态：" + STATUS_FLOW[statusIndex]);
        } else {
            System.out.println("\n订单 " + id + " 当前状态：已完成全部流程");
        }
    }
}

