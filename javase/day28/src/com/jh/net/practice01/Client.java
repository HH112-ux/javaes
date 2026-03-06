package com.jh.net.practice01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket =new Socket("localhost",6666);
        Scanner sc =new Scanner(System.in);
        PrintWriter pw =new PrintWriter(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("猜数字1-100");
        while (true){

            int n =sc.nextInt();
            pw.println(n);
            pw.flush();
            String s = br.readLine();
            System.out.println(s);
            if (s.equals("猜对了")){
                break;

            }

        }
        socket.close();
    }
}
