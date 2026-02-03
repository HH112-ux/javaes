package com.jh.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author jh
 * @project com.jh.io
 * @time 2026/2/2
 */
public class TestBuffered {
    public static void main(String[] args) throws IOException {
        /*FileWriter writer=new FileWriter("d:/aa/buffer.txt");
        BufferedWriter bufferedWriter=new BufferedWriter(writer);
        bufferedWriter.write("hello buffer");
        //bufferedWriter.write("\r\n");
        bufferedWriter.newLine();
        bufferedWriter.write("new line content");
        bufferedWriter.close();*/
        FileWriter writer = new FileWriter("d:/aa/10000.txt");
        BufferedWriter bw = new BufferedWriter(writer);
        for (int i = 0; i < 10000; i++) {
            writer.write(i+"/t");
        }
    }
}
