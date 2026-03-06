package com.jh.net.practice02;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        String fileName;
        while (true) {
            System.out.print("请输入要上传的文件名：");
            fileName = sc.nextLine();
            File file = new File(fileName);
            if (file.exists() && file.isFile()) break;
            System.out.println("文件不存在，请重新输入");
        }
        Socket socket = new Socket("localhost", 9999);
        PrintWriter pw = new PrintWriter(socket.getOutputStream());
        pw.println(fileName);
        pw.flush();
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String msg = br.readLine();
        if (!msg.equals("可以上传")) {
            System.out.println("服务端拒绝接收");
            socket.close();
            return;
        }
        FileInputStream fis = new FileInputStream(fileName);
        OutputStream os = socket.getOutputStream();
        byte[] buf = new byte[1024];
        int len;
        while ((len = fis.read(buf)) != -1) {
            os.write(buf, 0, len);
        }
        socket.shutdownOutput();
        System.out.println("上传文件成功");
        os.close();
        fis.close();
        socket.close();
    }
}