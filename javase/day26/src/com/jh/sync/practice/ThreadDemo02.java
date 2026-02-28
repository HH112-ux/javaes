package com.jh.sync.practice;

/**
 * @author jh
 * @project com.jh.sync.practice
 * @time 2026/2/27
 */
public class ThreadDemo02 {
    static final Object lock = new Object();
    static int step = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {


                synchronized (lock) {

                    while (step != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("生产小兵");
                    step = 1;
                    lock.notifyAll();
                }
            }
        }, "线程1").start();
        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {


                    while (step != 1) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("移动小兵");
                    step = 2;
                    lock.notifyAll();
                }
            }
        }, "线程2").start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                synchronized (lock) {
                    while (step != 2) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    step = 0;
                    System.out.println("开始攻击");
                    lock.notifyAll();
                }
            }
        }, "攻击").start();
    }
}
