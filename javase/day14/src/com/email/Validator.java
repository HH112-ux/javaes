package com.email;

import java.util.regex.Pattern;

/**
 * @author jh
 * @project com.email
 * @time 2026/1/16
 */
public class Validator {

    protected static void validateEmail(String[] emails) {
        int successCount = 0;
        int qqCount = 0;
        String emailRegex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        for (String email : emails) {
            email = email.trim();
            if (Pattern.matches(emailRegex, email)) {
                successCount++;
                System.out.println(email + "：邮件发送成功");
                if (email.endsWith("@qq.com")) {
                    qqCount++;
                }
            } else {
                System.out.println(email + "：邮件发送失败");
            }
        }
        System.out.println("发送" + successCount + "篇");
        System.out.println("发送给QQ邮箱：" + qqCount);
    }
}

