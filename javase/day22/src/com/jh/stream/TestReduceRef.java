package com.jh.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author jh
 * @project com.jh.stream
 * @time 2026/1/30
 */
public class TestReduceRef {
    public static void main(String[] args) {
        //求所有英雄的血量总和
        //求所有英雄中血量最高的英雄
        List<Hero> heroList = Arrays.asList(new Hero("亚瑟", 10000),
                new Hero("项羽", 14000),
                new Hero("小乔", 5000));
        //求和操作
        /* Integer reduce = heroList.stream().reduce(0,
        (sum, hero) -> sum += hero.getBlood(),
        Integer::sum);
        System.out.println(reduce);*/
        Integer x = heroList.stream().map(Hero::getBlood)
                .reduce(0,Integer::sum);
        System.out.println(x);
        //最高值
        Integer max = heroList.stream().map(Hero::getBlood).reduce(0,
                Integer::max);
        System.out.println(max);
    }
}
