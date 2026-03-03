package com.jh.reen;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jh
 * @project com.jh.reen
 * @time 2026/3/2
 */
public class Test03 {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        Fair fair = new Fair(lock);
       new Thread(fair,"t1").start();
       new Thread(fair,"t2").start();

    }
}

class Fair implements Runnable {
    private Integer num = 0;
    private ReentrantLock lock;

    public Fair(ReentrantLock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        while (num <= 100) {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+":"+num++);
            } finally {
                lock.unlock();
            }
        }

    }
}
