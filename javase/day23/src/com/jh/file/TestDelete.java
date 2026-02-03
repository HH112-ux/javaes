package com.jh.file;

import java.io.File;
import java.io.IOException;

/**
 * @author jh
 * @project com.jh.file
 * @time 2026/2/1
 */
public class TestDelete {
    public static void main(String[] args) throws IOException, InterruptedException {
      /*  //如果删除就返回true失败就返回false
        File file = new File("d:aa/hello.txt");
        file.createNewFile();
        boolean delete = file.delete();
        //虚拟机退出时散出文件
        //file.deleteOnExit();
        //让程序休眠
        Thread.sleep(5000);
        System.out.println("执行完成");*/
        
        //System.out.println("delete=" + delete);
        File tempFile = File.createTempFile("order", ".txt");
        System.out.println(tempFile.getAbsoluteFile());


    }
}
