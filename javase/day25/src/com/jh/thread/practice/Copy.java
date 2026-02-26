package com.jh.thread.practice;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jh
 * @project com.jh.thread.practice
 * @time 2026/2/26
 */
public class Copy {
        private static final AtomicLong totalCopied = new AtomicLong(0);
        private static long totalSize = 0;

        public static void main(String[] args) {
            File source = new File("D://aaa.txt");
            File dest = new File("D://ccc.txt");

            totalSize = source.length();
            int threadCount = 4;
            long blockSize = totalSize / threadCount;

            for (int i = 0; i < threadCount; i++) {
                final int threadId = i;
                new Thread(() -> {
                    try (FileInputStream in = new FileInputStream(source);
                         FileOutputStream out = new FileOutputStream(dest)) {

                        long start = threadId * blockSize;
                        long end = (threadId == threadCount - 1) ? totalSize : (threadId + 1) * blockSize;

                        in.getChannel().position(start);
                        out.getChannel().position(start);

                        byte[] buffer = new byte[8192];
                        int len;
                        while (in.getChannel().position() < end && (len = in.read(buffer)) != -1) {
                            out.write(buffer, 0, len);
                            totalCopied.addAndGet(len);

                            int progress = (int) (totalCopied.get() * 100 / totalSize);
                            System.out.print("\r复制进度: " + progress + "%");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        }
    }


