package com.jh.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author jh
 * @project com.jh.sync
 * @time 2026/2/27
 */
public class TestWaitSleep {
    static final Object LOCK=new Object();
    public static void main(String[] args) throws InterruptedException {
        Thread t1=new Thread(()->{
            synchronized (LOCK){
                System.out.println("线程1执行");
                try {
                // LOCK.wait(2000);
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1继续执行");
            }
        });
        t1.start();
        TimeUnit.SECONDS.sleep(1);
        synchronized (LOCK){
            System.out.println("主程序执行");
        }
    }
}