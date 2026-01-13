package com.factory.method;

import com.factory.simple.Factory;
import com.factory.simple.Human;
import com.factory.simple.Man;

/**
 * @author jh
 * @project Word3_4
 * @time 2026/1/12
 */
public class YanRopeFactory implements Factory {
    @Override
    public Human createHuman() {
        return new Man();
    }
}
