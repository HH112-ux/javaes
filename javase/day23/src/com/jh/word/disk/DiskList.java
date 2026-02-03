package com.jh.word.disk;

import java.io.File;
import java.text.SimpleDateFormat;

/**
 * @author jh
 * @project com.jh.word.disk
 * @time 2026/2/2
 */

public class DiskList {
    public static void main(String[] args) {
        File[] roots = File.listRoots();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        for (File root : roots) {
            System.out.println("=== 盘符: " + root.getAbsolutePath() + " ===");
            File[] files = root.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        System.out.printf("%s\t<DIR>\t\t%s\n",
                                sdf.format(file.lastModified()),
                                file.getName());
                    } else {
                        System.out.printf("%s\t%,d\t%s\n",
                                sdf.format(file.lastModified()),
                                file.length(),
                                file.getName());
                    }
                }
            }
        }
    }
}
