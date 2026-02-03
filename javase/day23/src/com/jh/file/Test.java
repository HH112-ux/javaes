package com.jh.file;

import java.io.File;

/**
 * @author jh
 * @project com.jh
 * @time 2026/2/1
 */
public class Test {
    public static void main(String[] args) {
        //常用方法
        File file=new File("d:/aa/xx.txt");
        //创建一个新的空文件
        //如果文件不存在，就建一个新文件，要是文件存在就会返回false
      /*  try {
            boolean b =file.createNewFile();
            System.out.println("b="+b);
        }catch (IOException e){
            e.printStackTrace();
        }*/
        //创建目录
        //同上
        //mkdir的方法只能创建当前一级目录，如果上级不存在，就会失败
        //midirs的方法就可以
        File dir = new File("d:/aa/yy/zz");
        //boolean b = dir.mkdir();
        boolean b=dir.mkdirs();
        System.out.println("b=" + b);
    }
}
