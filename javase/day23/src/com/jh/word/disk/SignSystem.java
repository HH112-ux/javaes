package com.jh.word.disk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author jh
 * @project com.jh.word.disk
 * @time 2026/2/2
 */
public class SignSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        String today = sdf.format(new Date());

        createTotalSignList(scanner);

        createTodaySignRecord(scanner, today);

        createAbsentList(today);

        scanner.close();
        System.out.println("✅ 签到系统流程已全部完成！");
    }

    /**
     * 生成总签到名单（签到表.txt）
     */
    private static void createTotalSignList(Scanner scanner) {
        System.out.println("\n=== 1. 生成总签到名单 ===");
        System.out.println("请输入所有人员姓名（输入“完成”结束）：");
        try (FileWriter fw = new FileWriter("签到表.txt")) {
            while (true) {
                String name = scanner.next().trim();
                if ("完成".equals(name)) break;
                fw.write(name + "\n");
            }
            System.out.println("总名单已保存到「签到表.txt」");
        } catch (IOException e) {
            System.err.println("生成总名单失败：" + e.getMessage());
        }
    }

    /**
     * 生成今日签到记录（xxx年xx月xx日_签到表.txt）
     */
    private static void createTodaySignRecord(Scanner scanner, String today) {
        String signRecordFile = today + "_签到表.txt";
        System.out.println("\n=== 2. 生成今日签到表 ===");
        System.out.println("请输入签到人员姓名（输入“完成”结束）：");
        try (FileWriter fw = new FileWriter(signRecordFile)) {
            while (true) {
                String name = scanner.next().trim();
                if ("完成".equals(name)) break;
                fw.write(name + "\n");
            }
            System.out.println("今日签到表已保存到「" + signRecordFile + "」");
        } catch (IOException e) {
            System.err.println("生成签到表失败：" + e.getMessage());
        }
    }

    /**
     * 生成今日缺席表（xxx年xx月xx日_缺席表.txt）
     */
    private static void createAbsentList(String today) {
        String absentFile = today + "_缺席表.txt";
        String totalListFile = "签到表.txt";
        String signRecordFile = today + "_签到表.txt";

        Set<String> totalNames = new HashSet<>();
        Set<String> signedNames = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(totalListFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                totalNames.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("读取总名单失败：" + e.getMessage());
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(signRecordFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                signedNames.add(line.trim());
            }
        } catch (IOException e) {
            System.err.println("读取签到表失败：" + e.getMessage());
            return;
        }

        totalNames.removeAll(signedNames);

        try (FileWriter fw = new FileWriter(absentFile)) {
            for (String name : totalNames) {
                fw.write(name + "\n");
            }
            System.out.println("今日缺席表已保存到「" + absentFile + "」");
        } catch (IOException e) {
            System.err.println("生成缺席表失败：" + e.getMessage());
        }
    }
}

