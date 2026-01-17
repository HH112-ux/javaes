package com.time;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author jh
 * @project com.time
 * @time 2026/1/17
 */
public class RandonTime {
    public static void main(String[] args) {
        Random random = new Random();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int num = random.nextInt(1001);
                System.out.println("随机数" + num);
            }
        }, 0, 2000);
    }
}


