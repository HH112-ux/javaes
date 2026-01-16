package com.email;

import java.util.Scanner;

import static com.email.Validator.validateEmail;

/**
 * @author jh
 * @project com.email
 * @time 2026/1/16
 */
public class EmailValidate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入多个邮箱地址");
        System.out.println("用分号分隔");
        String input = sc.nextLine();
        String[] emails = input.split(";");
        validateEmail(emails);
        sc.close();
    }
}
