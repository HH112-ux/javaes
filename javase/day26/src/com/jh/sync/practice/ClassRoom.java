package com.jh.sync.practice;

/**
 * @author jh
 * @project com.jh.sync.practice
 * @time 2026/2/27
 */
public class ClassRoom {
    static final Object lock = new Object();
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (lock) {
                try {
                    System.out.println("睡五秒");
                   lock.wait(5000);
                    System.out.println("该上课了");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"student").start();
        new Thread(()->{
            synchronized (lock) {
                for (int i = 0; i < 3; i++) {
                    System.out.println("上课了");
                }
                lock.notify();
            }
        },"teacher").start();
    }
}
