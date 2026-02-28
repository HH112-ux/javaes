package com.jh.sync.practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;

/**
 * @author jh
 * @project com.jh.sync.practice
 * @time 2026/2/27
 */
public class Massage {
    static final Object lock = new Object();
    static Queue<String> massages = new LinkedList<String>();
    static final int maxMassges = 2;
    static int massageCount = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(()->{
                while (true){
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    synchronized (lock) {
                    while (massages.size()==maxMassges){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    String mg ="xx"+getName()+" id:"+massageCount++;
                    System.out.println("生产信息："+mg);
                    massages.add(mg);
                    lock.notifyAll();
                }}
            },"生产线"+i).start();
        }
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        new Thread(()->{
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (lock){
                    while (massages.isEmpty()){
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    System.out.println("读取信息："+massages.poll());
                    lock.notifyAll();
                }
            }
        },"读取信息").start();
    }
    public static  String getName(){
        return Thread.currentThread().getName();
    }
}
