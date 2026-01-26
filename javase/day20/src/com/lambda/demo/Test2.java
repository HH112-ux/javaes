package com.lambda.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

/**
 * @author jh
 * @project com.lambda.demo
 * @time 2026/1/26
 */
public class Test2 {
    public static void main(String[] args) {
        System.out.println(randomuns10_100());
    }

    protected static List randomuns10_100() {
        List<Integer> numList = new ArrayList<>();
        Consumer<List<Integer>> addRandomNums = list -> {
            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                int randomNum = random.nextInt(100);
                list.add(randomNum);
            }
        };
        addRandomNums.accept(numList);
        return numList;
    }
}
