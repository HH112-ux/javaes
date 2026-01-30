package com.jh.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author jh
 * @project com.jh.stream
 * @time 2026/1/30
 */
public class TestSorted {
    public static void main(String[] args) {
        //自然排序
        List<Staff> list = Arrays.asList(
                new Staff("李白", 22, 10000),
                new Staff("杜甫", 28, 12000),
                new Staff("白居易", 25, 15000)
        );
          /*  Collections.sort(list);
            list.forEach(System.out::println);*/
//            System.out.println("_________________________");
        list.stream().filter(s -> s.getSal() >= 12000)
                .sorted().forEach(System.out::println);
    }
}

