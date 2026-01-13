package com.factory.method;

import com.factory.simple.Factory;
import com.factory.simple.Human;

/**
 * @author jh
 * @project Word3_4
 * @time 2026/1/12
 */
public class Nvwa {
    public void creatHuman(Factory factory) {
        Human human=factory.createHuman();
        human.eat();
        human.sleep();
        human.eat();
    }
}
