package com.jh.thread.practice;

import java.util.Random;

/**
 * @author jh
 * @project com.jh.thread.practice
 * @time 2026/2/26
 */
public class HareAndTortoise {
    private static final int FINISH_LINE = 100;
    private static int tortoisePos = 0;
    private static int harePos = 0;
    private static final Random random = new Random();
    private static boolean isGameOver = false;

    public static void main(String[] args) {
        Thread tortoise = new Thread(() -> {
            while (!isGameOver) {
                int step = random.nextInt(3) + 1;
                tortoisePos += step;
                if (tortoisePos > FINISH_LINE) tortoisePos = FINISH_LINE;
                System.out.println("乌龟跑到了 " + tortoisePos + " 米");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (tortoisePos >= FINISH_LINE) {
                    isGameOver = true;
                    System.out.println("乌龟获胜");
                }
            }
        });
        Thread hare = new Thread(() -> {
            while (!isGameOver) {
                int step = random.nextInt(3) + 3;
                harePos += step;
                if (harePos > FINISH_LINE) harePos = FINISH_LINE;
                System.out.println("兔子跑到了 " + harePos + " 米");
                if (harePos > 70) {
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (harePos >= FINISH_LINE) {
                    isGameOver = true;
                    System.out.println("兔子获胜");
                }
            }
        });
        hare.start();
        tortoise.start();

    }
}