package com.jh.word.copy;

import java.io.*;

/**
 * @author jh
 * @project com.jh.word.copy
 * @time 2026/2/2
 */
public class Copy {
        public static void copyDir(File source, File target) throws IOException {
            if (source.isDirectory()) {
                if (!target.exists()) {
                    target.mkdirs();
                }
                for (File file : source.listFiles()) {
                    copyDir(file, new File(target, file.getName()));
                }
            } else {
                try (InputStream in = new FileInputStream(source);
                     OutputStream out = new FileOutputStream(target)) {
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                }
            }
        }

        public static void main(String[] args) {
            try {
                copyDir(new File("D:\\source"), new File("D:\\target"));
                System.out.println("复制完成");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
