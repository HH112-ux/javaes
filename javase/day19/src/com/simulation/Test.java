package com.simulation;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import static com.simulation.Simulation.*;

/**
 * @author jh
 * @project com.simulation
 * @time 2026/1/22
 */
public class Test {
    public static void main(String[] args) {
        Queue<String> orderQueue = new LinkedList<>();
        Map<String, Integer> orderStatusMap = new HashMap<>();

        initOrders(orderQueue, orderStatusMap);
        processAllOrders(orderQueue, orderStatusMap);
        queryOrderStatus("ORDER_001", orderStatusMap);
    }
}
