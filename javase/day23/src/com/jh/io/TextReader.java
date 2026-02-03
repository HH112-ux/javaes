package com.jh.io;

import java.io.FileReader;
import java.io.IOException;

/**
 * @author jh
 * @project com.jh.io
 * @time 2026/2/2
 */
public class TextReader {
    public static void main(String[] args) throws IOException {
        FileReader reader=new FileReader("d://aa/buffer.txt");

        //每次读取一个字符，返回读取到的字符
       /* int read;
        while ((read = reader.read())!=-1) {//没有达到文件尾
            System.out.print((char) read);*/
       /* char[] chars=new char[1024];
        //将字符存入数组中，并返回一共读取到多少个字符
        int len = reader.read(chars);
        //System.out.println(read);
        String str=new String(chars,0,len);
        System.out.println(str);*/
        char[] chars=new char[1024];
        //读取指定长度
        int len = reader.read(chars, 0, 20);
        System.out.println(len);
        String str=new String(chars,0,len);
        System.out.println(str);
    }
}
