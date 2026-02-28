package com.jh.sync.practice;

/**
 * @author jh
 * @project com.jh.sync.practice
 * @time 2026/2/27
 */
public class ThreadDemo01 {
    static final Object lock = new Object();
static int step=0;
    public static void main(String[] args) {
        new Thread(()->{
            synchronized (lock) {
                if(step!=0){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("生产小兵");
                step=1;
                lock.notify();
            }
        },"生产线").start();
        new Thread(()->{
            synchronized (lock) {
                if(step!=1){
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                step=0;
                System.out.println("开始攻击");
            }
        },"攻击").start();
    }
}
