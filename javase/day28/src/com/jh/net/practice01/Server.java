package com.jh.net.practice01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(6666);
        System.out.println("等待链接");
        Socket client = server.accept();
        System.out.println("已连接");
        int answer = new Random().nextInt(100) + 1;
        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        PrintWriter pw =new PrintWriter(client.getOutputStream());
        while (true){
            String str= br.readLine();
            int num=Integer.parseInt(str);
            if (num<answer) {
                pw.println("小了");pw.flush();
            }
            else if (num>answer) {
                pw.println("大了");pw.flush();
            }else if (num==answer){
                pw.println("猜对了");pw.flush();
                break;
            }
        }
        client.close();
        server.close();
        br.close();

    }
}
