package com.jh.sync;

/**
 * @author jh
 * @project com.jh.sync
 * @time 2026/2/27
 */
public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        NumberObj obj = new NumberObj();
        Thread t1 =new Thread( ()->{
            for (int i = 0; i < 10000; i++) {
                obj.increment();
            }
        });
        Thread t2 =new Thread( ()->{
            for (int i = 0; i < 10000; i++) {
                obj.decrement();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(obj.getNumber());

    }
}

class NumberObj {
    private int x;

    public void increment() {
        synchronized (this) {
            x++;
        }
    }

    public synchronized void decrement() {
        x--;

    }

    public synchronized int getNumber() {
        return x;
    }

}
