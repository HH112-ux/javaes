package com.jh.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author jh
 * @project com.jh.stream
 * @time 2026/1/30
 */
public class TestCollectToCollecttion {
    public static void main(String[] args) {
        toList();
    }
    public static void toSet(){
        Set<Integer> set = Stream.of(1, 2, 3, 4, 5, 6, 7)
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toSet());
        set.forEach(System.out::println);
    }
    public static void toMap(){
        List<Emp> list= Arrays.asList(
                new Emp("李白",22,12000),
                new Emp("杜甫",28,10000),
                new Emp("白居易",25,11000),
                new Emp("李贺",24,8000)
        );
        Map<String, Emp> map = list.stream().filter(e -> e.getSal() >=
                        10000)
                .collect(Collectors.toMap(Emp::getName, e -> e));//key value
        map.forEach((k,v)-> System.out.println(k+"\t"+v));
    }
    public static void toCollection(){
        ArrayList<String> collect = Stream.of("abc", "ddd", "xx")
                .collect(Collectors.toCollection(ArrayList::new));
        collect.forEach(System.out::println);
    }

    private static void toList() {
        List<Emp> list = Arrays.asList(
                new Emp("李白", 22, 12000),
                new Emp("杜甫", 28, 10000),
                new Emp("白居易", 25, 11000),
                new Emp("李贺", 24, 8000)
        );
        List<Emp> newList = list.stream()
                .filter(e -> e.getName().startsWith("李"))
                .collect(Collectors.toList());
        newList.forEach(System.out::println);
    }
}
