package com.jh.net;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("localhost",1234);
        System.out.println("连接服务器");
        OutputStream os=socket.getOutputStream();
        PrintWriter pw=new PrintWriter(os);
        InputStream inputStream = socket.getInputStream();
        InputStreamReader isr=new InputStreamReader(inputStream);
        BufferedReader reader=new BufferedReader(isr);
        Scanner scanner=new Scanner(System.in);
        System.out.println("请录入聊天内容:");
        while (true) {
            String str = scanner.nextLine();
            pw.println(str);
            pw.flush();
            if ("bye".equals(str)){
                break;
            }
            str=reader.readLine();
            System.out.println("【服务器端】:"+str);
            if ("bye".equals(str)){
                break;
            }
        }
        reader.close();
        pw.close();
        socket.close();
    }
}
