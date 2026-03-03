package com.jh.reen.practice;

/**
 * @author jh
 * @project com.jh.reen.practice
 * @time 2026/3/3
 */

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledTasks {
    private static final String SRC_FILE = "d:\\aaa.txt";
    private static final String DST_FILE = "d:\\bbb.txt";

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

        executor.schedule(() -> {
            File src = new File(SRC_FILE);
            File dst = new File(DST_FILE);

            if (!src.exists()) {
                System.err.println("源文件 " + SRC_FILE + " 不存在，复制失败！");
                return;
            }

            try (InputStream in = new FileInputStream(src);
                 OutputStream out = new FileOutputStream(dst)) {

                byte[] buffer = new byte[1024];
                int len;
                while ((len = in.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                }
                System.out.println("文件已复制到：" + DST_FILE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, 2, TimeUnit.MINUTES);

        executor.schedule(() -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            String format = now.format(formatter);
            System.out.println("当前系统时间：" + format);
        }, 30, TimeUnit.SECONDS);

        executor.scheduleAtFixedRate(() -> {
            File file = new File(DST_FILE);
            if (file.exists()) {
                try (FileReader reader = new FileReader(file)) {
                    char[] buf = new char[1024];
                    int len;
                    System.out.println("文件内容：");
                    while ((len = reader.read(buf)) != -1) {
                        System.out.print(new String(buf, 0, len));
                    }
                    executor.shutdown();
                    System.exit(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("文件不存在，30秒后重试");
            }
        }, 1, 30, TimeUnit.SECONDS);
    }
}