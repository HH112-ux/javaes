package com.jh.io;

import java.io.*;

/**
 * @author jh
 * @project com.jh.io
 * @time 2026/2/2
 */
public class TextBuffereReader {
    public static void main(String[] args) throws IOException {
       /* FileReader reader=new FileReader("d:/aa/bufer.txt");
        BufferedReader bufferedReader=new BufferedReader(reader);
        String str;
        while ((str=bufferedReader.readLine())!=null){
            System.out.println(str);
        }
        bufferedReader.close();*/

    }
    public static void copy(File srcFile, File destFile) throws IOException {
        FileReader reader = new FileReader(srcFile);
        FileWriter writer = new FileWriter(destFile);
        int c;
        char[] chars = new char[1024];
        while ((c = reader.read(chars)) != -1) {
            writer.write(chars, 0, c);
        }
        reader.close();
        writer.close();
    }
    public static void copyBuffer(String srcFile, String destFile) throws
            IOException {
        FileReader reader = new FileReader(srcFile);
        FileWriter writer = new FileWriter(destFile);
        BufferedReader bufferedReader = new BufferedReader(reader);
        BufferedWriter bufferedWriter = new BufferedWriter(writer);
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            bufferedWriter.write(str);
            bufferedWriter.newLine();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
    public static void copyFile(String srcFile, String destFile) {
        try (FileReader reader = new FileReader(srcFile);
             FileWriter writer = new FileWriter(destFile);) {
            reader.transferTo(writer);//对文件复制的操作 jdk10版本后新增
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
