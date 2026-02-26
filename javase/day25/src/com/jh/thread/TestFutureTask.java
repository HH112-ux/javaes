package com.jh.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author jh
 * @project com.jh.thread
 * @time 2026/2/26
 */
public class TestFutureTask {
    public static void main(String[] args) throws ExecutionException,
            InterruptedException {
        //Future<Integer> future;
        FutureTask<Integer> task = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 1; i <= 100; i++) {
                sum += i;
            }
            Thread.sleep(1000);
            return sum;
        });
        new Thread(task).start();
       /* Thread.sleep(2000);
        task.cancel(true);
        System.out.println(task.isCancelled());
        System.out.println(task.isDone());*/
        /*new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + ":" + (int)
                        (Math.random() * 10));
            }
        }, "线程2").start();*/
        Integer x = null;//task.get();
        try {
            x = task.get(10000, TimeUnit.MILLISECONDS);
        } catch (TimeoutException e) {
            System.out.println("订单超时，退款");
            e.printStackTrace();
            return;
        }
        System.out.println(x);
    }
}
