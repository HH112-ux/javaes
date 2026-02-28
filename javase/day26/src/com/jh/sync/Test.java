package com.jh.sync;

/**
 * @author jh
 * @project com.jh.sync
 * @time 2026/2/27
 */
public class Test {
    static int x = 0;

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (Test.class) {
                    x--;
                }
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                synchronized (Test.class) {
                    x++;
                }
            }
        });
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(x);
    }
}

