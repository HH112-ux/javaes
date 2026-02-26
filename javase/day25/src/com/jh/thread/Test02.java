package com.jh.thread;

/**
 * @author jh
 * @project com.jh.thread
 * @time 2026/2/26
 */
public class Test02 {
    public static void main(String[] args) {
        FileUtil.readFile();
        new Thread(FileUtil::readFile).start();
        System.out.println("运行主程序");

    }
}
