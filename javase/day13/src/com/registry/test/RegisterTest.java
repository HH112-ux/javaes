package com.registry.test;

import com.registry.validator.UserValidator;

import java.util.Scanner;

/**
 * @author jh
 * @project com.registry.test
 * @time 2026/1/15
 */
public class RegisterTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入注册姓名");
        System.out.println("全英文");
        String name = scanner.nextLine();
        System.out.println("请输入注册密码");
        System.out.println("含数字、大小写字母");
        String password = scanner.nextLine();
        boolean isNameValid = UserValidator.validateName(name);
        boolean isPasswordValid = UserValidator.validatePassword(password);
        if (!isNameValid)
            System.out.println("姓名校验：" + "注册失败，名字要全为英文");
        if (!isPasswordValid)
            System.out.println("密码校验：" + "注册失败，密码需包含数字、大小写字母");
        System.out.println("注册结果：" + (isNameValid && isPasswordValid ? "成功" : "失败"));
        scanner.close();
    }
}