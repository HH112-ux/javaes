package com.jh.reen.practice;

import java.util.concurrent.locks.LockSupport;

/**
 * @author jh
 * @project com.jh.reen.practice
 * @time 2026/3/3
 */
public class LockSupportDemo {
    static Thread t1, t2, t3;
    public static void main(String[] args) {
        t1=new Thread(()->{

            LockSupport.park();
            System.out.println("下单");
            LockSupport.unpark(t2);
        });
        t2=new Thread(()->{
            LockSupport.park();
            System.out.println("付款");
            LockSupport.unpark(t3);
        });
        t3=new Thread(()->{
            LockSupport.park();
            System.out.println("积分");
            LockSupport.unpark(t1);
        });

            t1.start();
            t2.start();
            t3.start();
            LockSupport.unpark(t1);


    }
}
