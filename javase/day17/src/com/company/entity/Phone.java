package com.company.entity;

import java.util.*;

/**
 * @author jh
 * @project com.company.entity
 * @time 2026/1/21
 */
public class Phone {

    public static Set<String> generatePhoneNumbers(int count) {
        Set<String> phoneSet = new HashSet<>();
        String[] secondDigits = {"3", "5", "7", "8", "9"};
        Random random = new Random();

        while (phoneSet.size() < count) {
            StringBuilder phoneBuilder = new StringBuilder("1");

            phoneBuilder.append(secondDigits[random.nextInt(secondDigits.length)]);

            for (int i = 0; i < 9; i++) {
                phoneBuilder.append(random.nextInt(10));
            }
            phoneSet.add(phoneBuilder.toString());
        }
        return phoneSet;
    }


    public static List<String> drawNumbers(List<String> phoneList, int luckyCount) {
        if (phoneList.size() < luckyCount) {
            throw new IllegalArgumentException("电话号码数量不足，无法完成抽奖");
        }

        Collections.shuffle(phoneList);
        return phoneList.subList(0, luckyCount);
    }

    public static String maskPhone(String phone) {
        if (phone == null || phone.length() != 11) {
            return "无效手机号";
        }
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }
}

