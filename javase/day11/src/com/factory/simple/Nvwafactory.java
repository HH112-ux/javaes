package com.factory.simple;

/**
 * @author jh
 * @project Word3_4
 * @time 2026/1/12
 */
public class Nvwafactory {
    public static Human createHuman(String type) {
        if ("男人".equals(type)) {
            return new Man();
        } else if ("女人".equals(type)) {
            return new Woman();
        } else {
            throw new IllegalArgumentException("无法创建该类型的人");
        }
    }
}
