package com.jh.file;

import java.io.File;

/**
 * @author jh
 * @project com.jh.file
 * @time 2026/2/1
 */
public class TestFileOther {
    public static void main(String[] args) {
        File file = new File("d:/aa/healle.txt");

        // 获取文件名
        String name = file.getName();
        System.out.println("file name:" + name);

        // 获取目录名
        String parent = file.getParent();
        System.out.println("parent = " + parent);
        File parentFile = file.getParentFile();
        if (parentFile != null) {
            System.out.println(parentFile.getName());
        }

        //获取文件大小
        long length = file.length();
        System.out.println("length = " + length);

        // 设置文件只读
        file.setWritable(false);

        // 测试文件的可执行、可读、可写性
        boolean b = file.canExecute();
        System.out.println("canExecute = " + b);
        boolean b1 = file.canRead();
        System.out.println("canRead = " + b1);
        boolean b2 = file.canWrite();
        System.out.println("canWrite = " + b2);

        //  获取文件路径
        String path = file.getPath();
        System.out.println("path = " + path);
        String path1 = file.getAbsolutePath();
        System.out.println("absolutePath = " + path1);
        try {
            String path2 = file.getCanonicalPath();
            System.out.println("canonicalPath = " + path2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取文件最后修改时间戳
        long lastModified = file.lastModified();
        System.out.println("lastModified = " + lastModified);

        // 获取磁盘空间
        long totalSpace = file.getTotalSpace();
        System.out.println("totalSpace = " + totalSpace);
        long freeSpace = file.getFreeSpace();
        System.out.println("freeSpace = " + freeSpace);
        long usableSpace = file.getUsableSpace();
        System.out.println("usableSpace = " + usableSpace);

        // 获取所有磁盘分区
        File[] disks = File.listRoots();
        System.out.println("所有磁盘分区：");
        for (File disk : disks) {
            System.out.println(disk);
        }

        // boolean renameResult = file.renameTo(new File("src/hello_1.txt"));
        // System.out.println("renameResult = " + renameResult);

        boolean hidden = file.isHidden();
        System.out.println("hidden = " + hidden);
    }
}


