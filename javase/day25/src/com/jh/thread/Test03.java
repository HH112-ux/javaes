package com.jh.thread;

/**
 * @author jh
 * @project com.jh.thread
 * @time 2026/2/26
 */
public class Test03 {
    public static void main(String[] args) {
        System.out.println("主线程开始");
        new Thread(()-> {
            while (true) {
                System.out.println("hello");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        System.out.println("主线程结束");
    }
}

/*class MyRunnableImpl implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("hello");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/


