package com.jh.thread;

/**
 * @author jh
 * @project com.jh.thread
 * @time 2026/2/26
 */
public class Test01 {
    public static void main(String[] args) {
        System.out.println("主方法执行");
        MyThread myThread = new MyThread();
        myThread.setName("线程1");
        MyThread myThread1 = new MyThread();
        myThread1.setName("线程2");
        MyThread myThread2 = new MyThread();
        myThread2.setName("线程1");
        myThread.start();//自动调用run 方法
        myThread1.start();
        myThread2.start();
        System.out.println("主方法运行结束");
    }
}
class MyThread extends Thread{
    @Override
    public void run() {
        for (int i = 100; i > 0; i--) {
            System.out.println(MyThread.currentThread().getName()+":"+i);
        }
    }
}
