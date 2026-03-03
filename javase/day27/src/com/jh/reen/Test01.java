package com.jh.reen;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jh
 * @project com.jh.reen
 * @time 2026/3/2
 */
public class Test01 {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("线程1获取到锁");
                lock.lock();

            } finally {
                //lock.unlock();
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                System.out.println("线程2尝试获取锁");
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
                System.out.println("");
                return;
            }
            try {
                System.out.println("线程2获取到锁");
            } finally {
                lock.unlock();
            }
        });
        t2.start();
        TimeUnit.MILLISECONDS.sleep(1000);
        if (t2.isAlive()) {
            System.out.println("执行线程中断");
            t2.interrupt();
        } else {
            System.out.println("线程2执行完成");
        }
        System.out.println(lock.isLocked());
    }
}
