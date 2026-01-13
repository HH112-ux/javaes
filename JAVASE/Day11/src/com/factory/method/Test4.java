package com.factory.method;

/**
 * @author jh
 * @project Word3_4
 * @time 2026/1/12
 */
public class Test4 {
    public static void main(String[] args) {
        Nvwa nvwa = new Nvwa();
        nvwa.creatHuman(new YanRopeFactory());
        nvwa.creatHuman(new YinRopeFactory());
    }
}
