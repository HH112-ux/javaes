package com.jh.reen;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author jh
 * @project com.jh.reen
 * @time 2026/3/3
 */
public class TestPool {
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        Factory factory=new Factory();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2),factory);
        factory.setThreadName("线程1");
        executor.execute(() -> System.out.println("任务1：" + Thread.currentThread().getName()));
        factory.setThreadName("线程2");
        executor.execute(() -> System.out.println("任务2：" + Thread.currentThread().getName()));
        executor.shutdown();
    }
//测试救急线程
    private static void test2() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2));
        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务1：" + Thread.currentThread().getName());
        });
        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务2：" + Thread.currentThread().getName());
        });
        executor.execute(() -> System.out.println("任务3：" + Thread.currentThread().getName()));
        executor.execute(() -> System.out.println("任务4：" + Thread.currentThread().getName()));
        executor.execute(() -> System.out.println("任务5：" + Thread.currentThread().getName()));

        //executor.shutdown();
    }
    //测试拒绝
    private static void test3() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 3, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2),
                new ThreadPoolExecutor.AbortPolicy());
        executor = new ThreadPoolExecutor(2, 3, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2),
                new ThreadPoolExecutor.DiscardPolicy());
        executor = new ThreadPoolExecutor(2, 3, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2),
                new ThreadPoolExecutor.CallerRunsPolicy());
       /* executor = new ThreadPoolExecutor(2, 3, 60,
                TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(2),
                new ThreadPoolExecutor.DiscardOldestPolicy());*/

        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务1：" + Thread.currentThread().getName());
        });
        executor.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("任务2：" + Thread.currentThread().getName());
        });
        executor.execute(() -> System.out.println("任务3：" + Thread.currentThread().getName()));
        executor.execute(() -> System.out.println("任务4：" + Thread.currentThread().getName()));
        executor.execute(() -> System.out.println("任务5：" + Thread.currentThread().getName()));
        executor.execute(() -> System.out.println("任务6：" + Thread.currentThread().getName()));

        //executor.shutdown();
    }
}
class Factory implements ThreadFactory {
    private String threadName;

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r, threadName);
    }
}
