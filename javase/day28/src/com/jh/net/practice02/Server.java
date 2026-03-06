package com.jh.net.practice02;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        System.out.println("文件上传服务端已启动");
        Socket socket = server.accept();

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String fileName = br.readLine();
        System.out.println("客户端要上传：" + fileName);


        Scanner sc = new Scanner(System.in);
        System.out.print("是否接收？(y/n)：");
        String choice = sc.next();
        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);

        if (!"y".equalsIgnoreCase(choice)) {
            pw.println("拒绝接收");
            socket.close();
            server.close();
            return;
        }

        String dir;
        File dirFile;
        while (true) {
            System.out.print("请输入保存目录：");
            dir = sc.next();
            dirFile = new File(dir);
            if (dirFile.exists() && dirFile.isDirectory()) break;
            if (dirFile.mkdirs()) {
                System.out.println("目录创建成功");
                break;
            }
            System.out.println("目录创建失败，请重试");
        }
        pw.println("可以上传");

        FileOutputStream fos = new FileOutputStream(new File(dirFile, fileName));
        InputStream is = socket.getInputStream();
        byte[] buf = new byte[1024];
        int len;
        while ((len = is.read(buf)) != -1){
            fos.write(buf, 0, len);
            fos.flush();
        }

        System.out.println("上传文件成功");
        fos.close();
        socket.close();
        server.close();
    }
}
