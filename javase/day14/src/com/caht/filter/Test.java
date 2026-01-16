package com.caht.filter;

/**
 * @author jh
 * @project com.caht.filter
 * @time 2026/1/16
 */
public class Test {
    public static void main(String[] args) {
        ChatFilter filter=new ChatFilter();
        String[] sensitiveWords={"垃圾", "笨蛋", "脏话"};
        filter.setSensitiveWords(sensitiveWords);
        System.out.println(filter.filter("你真是个笨蛋，净说垃圾话"));
    }
}

