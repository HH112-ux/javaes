package com.jh.supermarket.util;


public class CheckUtil {
    public static boolean checkPhone(String phone) {
        if (phone == null) return false;
        return phone.matches("1[3-9]\\d{9}");
    }

    public static boolean checkAge(int age) {
        return age >= 18 && age <= 65;
    }

    public static boolean checkPrice(double price) {
        return price > 0;
    }

    public static boolean checkCount(int count) {
        return count > 0;
    }

    public static boolean checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{6,}$");
    }
}
