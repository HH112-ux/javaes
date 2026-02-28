package com.jh.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author jh
 * @project com.jh.sync
 * @time 2026/2/27
 */
public class TestWaitNotify {
    static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {

        Thread t1 =new Thread(()->{
            synchronized (lock) {
                System.out.println("线程一执行");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("线程一继续执行");
            }
        });
        t1.start();
        Thread t2 =new Thread(()->{
            synchronized (lock) {
                System.out.println("线程2执行");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("线程2继续执行");
            }
        });
        t2.start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("唤醒其他线程");
        synchronized (lock) {
        lock.notify();}
    }
}
