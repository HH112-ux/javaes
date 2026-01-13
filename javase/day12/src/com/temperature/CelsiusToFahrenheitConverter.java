package com.temperature;

import java.util.Scanner;

/**
 * @author jh
 * @project com.temperature
 * @time 2026/1/13
 */
public class CelsiusToFahrenheitConverter {
    public void convertCelsiusToFahrenheit() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("请输入摄氏温度");
            double celsius = scanner.nextDouble();
            double fahrenheit = celsius * 1.8 + 32;
            System.out.printf("摄氏温度 %.2f°C 转换为华氏温度为 %.2f°F%n", celsius,fahrenheit);
        } catch (java.util.InputMismatchException e) {
            System.out.println("请输入有效的数字类型温度");
        } finally {
            scanner.close();
        }
    }
}
