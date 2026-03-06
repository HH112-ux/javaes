package com.jh.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket server=new ServerSocket(1234);
        System.out.println("等待连接...");

        Socket client = server.accept();
        /*InetAddress address = client.getInetAddress();
        System.out.println(address.getHostAddress());
        System.out.println(address.getHostName());
        System.out.println(client.getPort());
        System.out.println(client.getLocalPort());
        System.out.println("已经有客户端连接...");*/
        InputStream inputStream = client.getInputStream();
        InputStreamReader isr=new InputStreamReader(inputStream);
        BufferedReader reader=new BufferedReader(isr);
        OutputStream outputStream = client.getOutputStream();
        PrintWriter pw=new PrintWriter(outputStream);
        Scanner scanner=new Scanner(System.in);
        while (true) {
            String str = reader.readLine();
            System.out.println("【客户端】:" + str);
            if ("bye".equals(str)){
                break;
            }
            str=scanner.nextLine();
            pw.println(str);
            pw.flush();
            if ("bye".equals(str)){ break;
            }
        }
        reader.close();
        client.close();
        server.close();
    }
}