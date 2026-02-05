package com.jh.inputoutput;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author jh
 * @project com.jh.inputoutput
 * @time 2026/2/4
 */
public class TestInputOutput {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("d:/aa/hello.txt");
        InputStreamReader isr=new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br=new BufferedReader(isr);
        String read ;
        while ((read = br.readLine()) != null)
            System.out.println(read);
        fis.close();
    }
}
