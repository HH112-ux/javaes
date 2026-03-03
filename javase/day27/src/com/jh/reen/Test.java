package com.jh.reen;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jh
 * @project com.jh
 * @time 2026/3/2
 */
public class Test {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            int Index = 1;
            try {
                lock.lock();
                System.out.println("第一次加锁");
                while (true) {
                    try {
                        lock.lock();
                        System.out.println("这是第" + (++Index) + "加锁");
                        if (Index == 10) {
                            break;
                        }
                    } finally {
                        lock.unlock();
                        System.out.println("这是第"+(Index)+"解说");
                    }
                }
            } finally {
                lock.unlock();
                System.out.println("最后解说");
            }
        }).start();
        new Thread(() -> {
            try {
                lock.lock();
                for (int i = 0; i < 3; i++) {
                    System.out.println("线程任务："+Thread.currentThread().getName());
                }
            }finally {
                lock.unlock();
            }

        }).start();
    }
}
