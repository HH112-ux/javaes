package com.jh.word;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class LotterySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Random random = new Random();

        System.out.print("请输入彩票类型（3D/5D）：");
        String type = sc.next();
        System.out.print("请输入注数：");
        int count = sc.nextInt();

        try (FileWriter fw = new FileWriter("彩票记录.txt", true)) {
            for (int i = 0; i < count; i++) {
                String serial = String.format("%010d", random.nextInt(1000000000));
                String number = generateNumber(type);
                fw.write(String.format("%s\t%s\t%s\n",
                        sdf.format(new Date()), serial, number));
            }
            System.out.println("彩票已生成并写入文件");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateNumber(String type) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        int len = "3D".equals(type) ? 3 : 5;
        for (int i = 0; i < len; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}



