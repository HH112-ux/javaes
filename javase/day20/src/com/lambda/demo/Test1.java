package com.lambda.demo;

import java.util.Scanner;
import java.util.function.BiFunction;

/**
 * @author jh
 * @project com.lambda.demo
 * @time 2026/1/26
 */
public class Test1 {

    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> addFunc = (a, b) -> a + b;
        BiFunction<Integer, Integer, Integer> subtractionFunc = (a, b) -> a - b;
        BiFunction<Integer, Integer, Integer> multiplicationFunc = (a, b) -> a * b;
        BiFunction<Integer, Integer, Integer> divisionFunc = (a, b) -> a / b;
        Scanner sc = new Scanner(System.in);
        int nums1 = sc.nextInt();
        int nums2 = sc.nextInt();
        sc.close();
        int sum = addFunc.apply(nums1, nums2);
        int sum2 = subtractionFunc.apply(nums1, nums2);
        int sum3 = multiplicationFunc.apply(nums1, nums2);
        int sum4 = divisionFunc.apply(nums1, nums2);
        System.out.println(nums1 + " + " + nums2 + " = " + sum);
        System.out.println(nums1 + " - " + nums2 + " = " + sum2);
        System.out.println(nums1 + " * " + nums2 + " = " + sum3);
        System.out.println(nums1 + " / " + nums2 + " = " + sum4);
    }
}

