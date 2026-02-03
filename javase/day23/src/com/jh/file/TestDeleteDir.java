package com.jh.file;

import java.io.File;

/**
 * @author jh
 * @project com.jh.file
 * @time 2026/2/1
 */
public class TestDeleteDir {
    public static void main(String[] args) {
        //在删除目录时要求为空
      /*  File dir =new File("d；/aa/bb");
        //获取目录下全部文件
      *//*  File[] files = dir.listFiles();
        for (File file : files) {
            //System.out.println(file.getName());
            file.delete();
        }

        boolean delete = dir.delete();
        System.out.println("delete = " + delete);*/

    }

    //递归删除目录
    public static void deleteDir(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDir(file);
                }
                file.delete();
            }
            dir.delete();
        }

        dir.delete();
    }
}
