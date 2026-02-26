package com.jh.thread;

import java.util.Random;

/**
 * @author jh
 * @project com.jh.thread
 * @time 2026/2/26
 */
public class FileUtil {
    public static void readFile() {
        int t=new Random().nextInt(2000);
        try {
            Thread.sleep(t);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("文件读取成功，耗费"+t+"ms");
    }

}
