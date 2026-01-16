package com.idcard;

import java.util.Scanner;

import static com.idcard.Validator.vslidateIdCard;

/**
 * @author jh
 * @project com.idcard
 * @time 2026/1/16
 */
public class IdCardValidate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入18位身份证号码：");
        String idCard = scanner.nextLine().trim();

        vslidateIdCard(idCard);
        scanner.close();
    }
}
