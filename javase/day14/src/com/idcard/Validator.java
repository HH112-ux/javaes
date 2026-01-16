package com.idcard;

import java.util.regex.Pattern;

/**
 * @author jh
 * @project com.idcard
 * @time 2026/1/16
 */
public class Validator {
    protected static void vslidateIdCard(String idCard) {
        String regex = "^\\d{17}[\\dXx]$";
        boolean isValid = Pattern.matches(regex, idCard);

        if (isValid) {
            String masked = idCard.substring(0, 6) + "********" + idCard.substring(14);
            System.out.println("脱敏后：" + masked);

            char genderChar = idCard.charAt(16);
            int genderCode = genderChar - '0';
            System.out.println("性别：" + (genderCode % 2 == 1 ? "先生" : "女士"));
        } else {
            System.out.println("身份证号码格式不正确！");
        }
    }
}


