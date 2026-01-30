package com.jh.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author jh
 * @project com.jh.stream
 * @time 2026/1/30
 */

public class TestMap {
    public static void main(String[] args) {
        List<Double> list = Arrays.asList(3.145, 4.556, 5.432, 6.738);
        list.stream().map(TestMap::k2).forEach(System.out::println);
    }

    public static double k2(double x) {
        return (int) ((x * 100) + 0.5) / 100d;
    }

    public static void getHeroName() {
        List<Hero> heroList = Arrays.asList(new Hero("亚瑟", 10000),
                new Hero("项羽", 14000),
                new Hero("小乔", 5000));
        heroList.stream().map(Hero::getName).forEach(System.out::println);
    }

    public static void addBlood() {
        List<Hero> heroList = Arrays.asList(new Hero("亚瑟", 10000),
                new Hero("项羽", 14000),
                new Hero("小乔", 5000));
        heroList.stream().map(h -> {
            Hero hero = new Hero(h.getName(), h.getBlood());
            hero.setBlood(hero.getBlood() + 1000);
            return hero;
        }).forEach(System.out::println);

        System.out.println("_________________________");
        heroList.forEach(System.out::println);
    }

}
