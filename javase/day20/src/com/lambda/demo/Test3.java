package com.lambda.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.lambda.demo.Test2.randomuns10_100;

/**
 * @author jh
 * @project com.lambda.demo
 * @time 2026/1/27
 */
public class Test3 {
    public static void main(String[] args) {
        List<Integer> randomNumList = randomuns10_100();
        List<Integer> evenNumList = new ArrayList<>();
        Predicate<Integer> isEven = num -> num % 2 == 0;
        for (Integer num : randomNumList) {
            if (isEven.test(num)) {
                evenNumList.add(num);
            }
        }
        System.out.println(randomNumList);
        System.out.println(evenNumList);
    }
}

