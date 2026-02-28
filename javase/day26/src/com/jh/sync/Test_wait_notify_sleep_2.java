package com.jh.sync;

import java.util.concurrent.TimeUnit;

/**
 * @author jh
 * @project com.jh.sync
 * @time 2026/2/27
 */
public class Test_wait_notify_sleep_2 {
    static final Object lock = new Object();
    static boolean b = false;
    static boolean a = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            synchronized (lock) {
                System.out.println(getName() + "是否拿到工资：" + b);
                while (!b) {
                    System.out.println(getName() + "不工作");
                    try {
                        //TimeUnit.SECONDS.sleep(2);
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(getName() + "是否拿到工资：" + b);
                if (b) {
                    System.out.println(getName() + "拿到工资干活");
                }
            }
        }, "李白").start();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println(getName() + "是否拿到工资：" + a);
                while (!a) {
                    System.out.println(getName() + "不工作");
                    try {
                        //TimeUnit.SECONDS.sleep(2);
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(getName() + "是否拿到工资：" + a);
                if (a) {
                    System.out.println(getName() + "拿到工资干活");
                }
            }
        }, "杜甫").start();
       /* for (int i = 0; i < 4; i++) {
            new Thread(()->{
                synchronized (lock) {
                    System.out.println(getName()+"开始工做");
                }
            },"其他人"+i).start();
            
        }*/
            TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("发工资了");
                a = true;
                //lock.notify();
                lock.notifyAll();
            }
        }).start();
        TimeUnit.SECONDS.sleep(3);
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("发工资了");
                b = true;
                //lock.notify();
                lock.notifyAll();
            }
        }).start();
    }

    public static String getName() {
        return Thread.currentThread().getName();
    }
}
