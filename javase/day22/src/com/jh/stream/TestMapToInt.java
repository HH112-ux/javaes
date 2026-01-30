package com.jh.stream;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author jh
 * @project com.jh.stream
 * @time 2026/1/30
 */
public class TestMapToInt {
    public static void main(String[] args) {
        //testToInt1();
        // maxAndMin();
        // sumAndAvg();
        //summary();
        range();
    }

    public static void range() {
        System.out.println(IntStream.rangeClosed(1, 100).sum());
        int sum = IntStream.range(1, 100).sum();
        System.out.println(sum);
        Stream<Integer> stream = IntStream.rangeClosed(1, 10).boxed();
        stream.forEach(System.out::println);
    }

    public static void summary() {
        IntSummaryStatistics x = Stream.of("1", "2", "3")
                .mapToInt(Integer::valueOf).summaryStatistics();
        System.out.println(x.getCount());
        System.out.println(x.getMax());
        System.out.println(x.getMin());
        System.out.println(x.getSum());
        System.out.println(x.getAverage());
    }

    public static void sumAndAvgAndCount() {
        List<Integer> list = Arrays.asList(12, 23, 34, 43, 32, 21, 9, 56, 78, 21);
        int sum = list.stream().mapToInt(Number::intValue).sum();
        OptionalDouble average =
                list.stream().mapToInt(Number::intValue).average();
        long count = list.stream().mapToInt(Number::intValue).count();
        System.out.println("count = " + count);
        System.out.println("sum = " + sum);
        System.out.println("average.getAsDouble() = " +
                average.getAsDouble());
    }

    public static void maxAndMin() {
        List<Integer> list = Arrays.asList(12, 23, 34, 43, 32, 21, 9, 56, 78, 21);
        OptionalInt max = list.stream().mapToInt(Number::intValue).max();
        OptionalInt min = list.stream().mapToInt(Number::intValue).min();
        System.out.println(max.getAsInt());
        System.out.println(min.getAsInt());
    }
    public static void testToInt1(){
        List<String> list=Arrays.asList("aabc","bbb","werwe","xxx");
        list.stream().mapToInt(String::length).forEach(System.out::println);
    }
    public static void testToInt(){
        List<Integer> list= Arrays.asList(12,23,34,45,56);
        list.stream().mapToInt(x->x*10).forEach(System.out::println);
    }
}
