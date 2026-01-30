package com.jh.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author jh
 * @project com.jh.stream
 * @time 2026/1/30
 */

public class TestSortedComparator {
    public static void main(String[] args) {
        List<Emp> list = Arrays.asList(
                new Emp("李白", 22, 10000),
                new Emp("杜甫", 28, 12000),
                new Emp("白居易", 22, 18000)
        );
        //按年龄升序排列
        /*list.stream().sorted(Comparator.comparingInt(Emp::getAge))
                .forEach(System.out::println);*/
        //按工资降序排列

        /*list.stream().sorted(Comparator.comparingInt(Emp::getSal).reversed()).forEach(System.out::println);*/
        //按年龄升序排列，如果年龄相同，再按工资升序排列
/*
        list.stream().sorted(Comparator.comparingInt(Emp::getAge)
                .thenComparing(Emp::getSal)).forEach(System.out::println);
*/
        //按年龄升序排列，如果年龄相同，按工资降序排列
        list.stream().sorted((o1, o2) -> {
            int compare = Integer.compare(o1.getAge(), o2.getAge());
            if (compare == 0) {
                return Integer.compare(o2.getSal(), o1.getSal());
            } else {
                return compare;
            }
        }).forEach(System.out::println);
    }
}

