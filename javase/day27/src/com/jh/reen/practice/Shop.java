package com.jh.reen.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jh
 * @project com.jh.reen.practice
 * @time 2026/3/3
 */
public class Shop {
    static int step = 0;
    static ReentrantLock lock = new ReentrantLock();
    static Condition step01 = lock.newCondition();
    static Condition step02 = lock.newCondition();
    static Condition step03 = lock.newCondition();
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                lock.lock();
                try {
                    while (step != 0) {
                        step01.await();
                    }
                    System.out.println("购买商品下单");
                    step = 1;
                    step02.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            },"购买线程").start();
            new Thread(() -> {
                lock.lock();
                try {
                    while (step != 1) {
                        step02.await();
                    }
                    System.out.println("微信付款");
                    step = 2;
                    step03.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            },"付款线程").start();
            new Thread(() -> {
                lock.lock();
                try {
                    while (step != 2) {
                        step03.await();
                    }
                    System.out.println("用户积分");
                    step = 0;
                    step01.signal();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                }
            },"积分线程").start();

        }
    }
}
