package com.jh.sync.practice;

import java.util.concurrent.TimeUnit;

/**
 * @author jh
 * @project com.jh.sync.practice
 * @time 2026/2/27
 */
public class PassCave {
    static final Object lock = new Object();
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(()->{
               synchronized (lock) {
                   System.out.println(getName()+"进入山洞");
                   System.out.println("________________");
                   try {
                       TimeUnit.SECONDS.sleep(5);
                   } catch (InterruptedException e) {
                       throw new RuntimeException(e);
                   }
                   System.out.println(getName()+"走出山洞");
                   System.out.println("________________");
               }
            },"人"+i).start();
        }
    }
    public static  String getName(){
        return Thread.currentThread().getName();
    }
}
