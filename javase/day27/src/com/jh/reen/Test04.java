package com.jh.reen;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jh
 * @project com.jh.sync
 * @time 2026/2/27
 */
public class Test04 {
    static final ReentrantLock lock = new ReentrantLock();
    static boolean b = false;
    static boolean a = false;
    static Condition bc = lock.newCondition();
    static Condition ac = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
           try {
               lock.lock();
                System.out.println(getName() + "是否拿到工资：" + b);
                while (!b) {
                    System.out.println(getName() + "不工作");
                    try {
                        //TimeUnit.SECONDS.sleep(2);
                        //lock.wait();
                        bc.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(getName() + "是否拿到工资：" + b);
                if (b) {
                    System.out.println(getName() + "拿到工资干活");
                }
            }
           finally {
               lock.unlock();
           }
        }, "李白").start();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(getName() + "是否拿到工资：" + a);
                while (!a) {
                    System.out.println(getName() + "不工作");
                    try {
                        //TimeUnit.SECONDS.sleep(2);
                        //lock.wait();
                        ac.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println(getName() + "是否拿到工资：" + a);
                if (a) {
                    System.out.println(getName() + "拿到工资干活");
                }
            } finally {
                lock.unlock();
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
            try {
                lock.lock();
                System.out.println("发工资了");
                a = true;
                //lock.notify();
                //lock.notifyAll();
                ac.signal();
            }finally {
                lock.unlock();
            }
        }).start();
        TimeUnit.SECONDS.sleep(3);
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println("发工资了");
                b = true;
                //lock.notify();
                //lock.notifyAll();
                bc.signal();
            }finally {
                lock.unlock();
            }
        }).start();
    }

    public static String getName() {
        return Thread.currentThread().getName();
    }
}
