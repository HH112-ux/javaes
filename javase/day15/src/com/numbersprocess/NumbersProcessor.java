package com.numbersprocess;

import java.util.Scanner;

/**
 * @author jh
 * @project com.numsprocess
 * @time 2026/1/17
 */

public class NumbersProcessor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入数字并用逗号隔开");
        String input = sc.nextLine();
        sc.close();
        String[] numsString = input.split(",");
        for (String str : numsString) {
            try {
                double num = Double.parseDouble(str.trim());
                double result = num * 10;
                BigDecimal bd = new BigDecimal(result);
                bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                System.out.println(bd);
            } catch (NumberFormatException e) {
                System.out.println(str + "这个不是数字");
            }
        }
    }
}



