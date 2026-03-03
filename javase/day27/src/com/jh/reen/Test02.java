package com.jh.reen;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jh
 * @project com.jh.reen
 * @time 2026/3/2
 */
public class Test02 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            System.out.println("尝试获取锁");
            //boolean b = lock.tryLock();
            boolean b = false;
            try {
                b = lock.tryLock(3, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (!b) {
                System.out.println("失败");
                return;
            }
            try {
                System.out.println("拿到了");
                System.out.println("执行");
            } finally {
                lock.unlock();
            }
        });
        lock.lock();
        try {
            System.out.println("主线拿到锁");

            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
        t1.start();

    }
}
