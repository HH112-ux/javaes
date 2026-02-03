package com.jh.io;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author jh
 * @project com.jh.io
 * @time 2026/2/2
 */
public class Test {
    public static void main(String[] args){
        //try with resource
        try (FileWriter writer = new FileWriter("d:/aaheallo.txt")) {
            for (int i = 0; i < 1000; i++) {
                writer.write(i + "\t");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        writer.write("hello");
      /*  writer.flush();//刷新流
        //writer.close();//打开流，读写后都要关闭*/

    }
}
