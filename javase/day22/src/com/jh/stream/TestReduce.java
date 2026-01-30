package com.jh.stream;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author jh
 * @project com.jh.stream
 * @time 2026/1/30
 */
public class TestReduce {
    public static void main(String[] args) {
        //求和
        Stream<Integer> stream = Stream.of(1, 12, 3, 4, 5);
        //使用mapToInt转成整数据的操作
       /* int sum = stream.mapToInt(Integer::intValue).sum();
        System.out.println(sum);*/
        //使用归约方式1
       /* Optional<Integer> reduce = stream.reduce(Integer::sum);
        System.out.println(reduce.get());*/
        //使用归约方式2
        /*Integer reduce = stream.reduce(0, Integer::sum);
        System.out.println(reduce);*/
        //求乘积
       /* Optional<Integer> reduce = stream.reduce((x, y) -> x * y);
        System.out.println(reduce.get());
        Integer reduce1 = stream.reduce(1, (x, y) -> x * y);
        System.out.println(reduce1);*/
        //求最大值
        /*Optional<Integer> reduce = stream.reduce((x, y) -> x > y ? x : y);
        System.out.println(reduce.get());*/
        //求最大值2
        /*Optional<Integer> reduce = stream.reduce(Integer::max);
        System.out.println(reduce.get());*/
        //求最小值
        Optional<Integer> reduce = stream.reduce(Integer::min);
        System.out.println(reduce.get());
    }
}

