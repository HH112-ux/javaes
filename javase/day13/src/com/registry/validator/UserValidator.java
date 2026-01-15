package com.registry.validator;

import java.util.regex.Pattern;

/**
 * @author jh
 * @project com.registry.validator
 * @time 2026/1/15
 */
public class UserValidator {
    private static final String nameRuge = "^[a-zA-Z]+$";

    private static final String passwordRuge = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z]).+$";

    private static final Pattern rightName = Pattern.compile(nameRuge);
    private static final Pattern rightPassword = Pattern.compile(passwordRuge);

    public static boolean validateName(String name) {
        if (name == null || name.isEmpty()) {
            return false;
        }
        return rightName.matcher(name).matches();
    }
    public static boolean validatePassword(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }
        return rightPassword.matcher(password).matches();
    }
}