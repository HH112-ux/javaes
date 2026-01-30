package com.jh.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author jh
 * @project com.jh.stream
 * @time 2026/1/30
 */
public class TestFlatMap {
    public static void main(String[] args) {
        String str = "a,b,c,d,e";
        String str1 = "m,n,j";
        List<String> list = Arrays.asList(str, str1);
        Stream<String> stream = list.stream().flatMap(TestFlatMap::f);
        stream.forEach(System.out::println);
    }
    public static Stream<String> f(String str) {
        String[] strs = str.split(",");
        Stream<String> stream = Arrays.stream(strs);
        return stream;
    }
}
