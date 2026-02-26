package com.jh.thread.practice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author jh
 * @project com.jh.thread.practice
 * @time 2026/2/26
 */
public class Sum {

    public static void main(String[] args) {

        FutureTask<Integer> task1 = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 0; i <= 500; i++) {
                sum += i;
            }
            System.out.println(sum);
            return sum;
        });
        FutureTask<Integer> task2 = new FutureTask<>(() -> {
            int sum = 0;
            for (int i = 501; i <= 1000; i++) {
                sum += i;
            }
            System.out.println(sum);
            return sum;
        });
        new Thread(task1).start();
        new Thread(task2).start();
        try {
            System.out.println(task1.get() + task2.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

    }
}
