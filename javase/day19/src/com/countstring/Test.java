package com.countstring;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * @author jh
 * @project com.countstring
 * @time 2026/1/24
 */
public class Test {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("请录入一行字符串");
        String str = scanner.next();
        scanner.close();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        Set<Map.Entry<Character, Integer>> entries = map.entrySet();
        for (Map.Entry<Character, Integer> entry : entries) {
            System.out.println(entry.getKey() + "出现" + entry.getValue() + "次");
        }
    }
}
    

