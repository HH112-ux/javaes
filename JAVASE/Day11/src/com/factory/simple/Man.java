package com.factory.simple;

/**
 * @author jh
 * @project Word3_4
 * @time 2026/1/12
 */
public class Man implements Human {
    @Override
    public void eat() {
        System.out.println("男人吃饭");
    }
    @Override
    public void sleep() {
        System.out.println("男人睡觉");
    }
    @Override
    public void speak() {
        System.out.println("男人说话");
    }
}
