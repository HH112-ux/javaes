package com.jh.reen.practice;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author jh
 * @project com.jh.reen.practice
 * @time 2026/3/3
 */
public class Demo01 {
    static int step=0;
   static ReentrantLock lock = new ReentrantLock();
   static Condition condition01 = lock.newCondition();
   static Condition condition02 = lock.newCondition();
    public static void main(String[] args) {

        new Thread(()->{
            try{
                lock.lock();
                while (step!=0){
                    condition01.await();
                }
                System.out.println("开始生产小兵");
                step=1;
                condition02.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        },"生产线").start();
        new Thread(()->{
            try{
                lock.lock();
                while (step!=1){
                    condition02.await();
                }
                System.out.println("小兵开始攻击");
                step=0;
                condition01.signal();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        },"攻击线程").start();
    }
}
