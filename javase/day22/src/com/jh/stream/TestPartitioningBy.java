package com.jh.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jh
 * @project com.jh.stream
 * @time 2026/1/30
 */
public class TestPartitioningBy {
    public static void main(String[] args) {
        List<Emp> list = Arrays.asList(
                new Emp("李白", 22, 12000),
                new Emp("杜甫", 25, 9000),
                new Emp("白居易", 26, 14000),
                new Emp("李贺", 23, 16000)
        );
        //true false
        Map<Boolean, List<Emp>> collect = list.stream().collect(Collectors.partitioningBy(x -> x.getSal() >=
                10000));
        collect.forEach((k, v) -> {
            System.out.println(k);
            v.forEach(System.out::println);
        });
    }

}
