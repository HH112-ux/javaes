package com.jh.inputoutput;

import java.io.*;

/**
 * @author jh
 * @project com.jh.inputoutput
 * @time 2026/2/5
 */

public class TestCopy {
    public static void main(String[] args) throws IOException {
        copyBuffer(new File("C:" + File.separator +
                        "Users\\Administrator\\Desktop\\resource\\nacos-server-2.0.4.zip"),
                new File("d:/aa/nacos-server-2.0.4.zip"));
    }

    public static void copy(File srcFile, File destFile) throws IOException {
        long t1 = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        int x;
        while ((x = fis.read()) != -1) {
            fos.write(x);
        }
        fis.close();
        fos.close();
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }

    public static void copyBuffer(File srcFile, File destFile) throws
            IOException {
        long t1 = System.currentTimeMillis();
        FileInputStream fis = new FileInputStream(srcFile);
        FileOutputStream fos = new FileOutputStream(destFile);
        BufferedInputStream bis = new BufferedInputStream(fis);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        byte[] bytes = new byte[1024];
        int x;
        while ((x = bis.read(bytes)) != -1) {
            bos.write(bytes, 0, x);
        }
        bis.close();
        bos.close();
        long t2 = System.currentTimeMillis();
        System.out.println(t2 - t1);
    }
}
