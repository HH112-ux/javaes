package com.jh.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author jh
 * @project com.jh.io
 * @time 2026/2/2
 */
public class TestFileWriter {
    public static void main(String[] args) throws IOException {
        //一
        //FileWriter writer = new FileWriter("d:/aa/hello.txt");
        //er
        //FileWriter writer =new FileWriter("d:/aa/hello.txt",true);
        //三
        File file=new File("d:/aa/test.txt");
        //FileWriter fw=new FileWriter(file);
        //四
        //FileWriter writer=new FileWriter(file,true);
        //五
        FileWriter writer=new FileWriter("d:/aa/test1.txt", StandardCharsets.UTF_8);

        //writer.write("国");
        char[] chars={'中','国','人'};
        //writer.write(chars);
        //writer.write(chars,0,2);
        String srt="我是中国人";
        writer.write(srt,2,3);
        writer.close();

    }
}
