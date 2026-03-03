package com.jh.reen.practice;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jh
 * @project com.jh.reen.practice
 * @time 2026/3/3
 */
public class TicketRush {
    static int tickets = 10;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 0, TimeUnit.SECONDS
                , new LinkedBlockingDeque<>());
        for (int i = 0; i < 30; i++) {
            final int id = i + 1;
            executor.execute(() -> {
                synchronized (TicketRush.class) {
                    if (tickets <= 0) {

                        System.out.println(id + "抢票失败");
                    } else
                        System.out.println(id + "抢票成功"+"余票："+--tickets);
                }
            });
        }
        executor.shutdown();
    }
}

