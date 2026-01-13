package com.factory.simple;

/**
 * @author jh
 * @project Word3_4
 * @time 2026/1/12
 */
public class Test3 {
    public static void main(String[] args) {
        Human man=Nvwafactory.createHuman("男人");
        Human wuman=Nvwafactory.createHuman("女人");
        man.eat();
        wuman.eat();
        man.sleep();
        wuman.sleep();
        man.speak();
        wuman.speak();
    }
}
