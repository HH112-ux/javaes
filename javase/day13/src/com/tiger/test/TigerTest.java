package com.tiger.test;

import com.tiger.entity.Tiger;

/**
 * @author jh
 * @project com.tiger.test
 * @time 2026/1/15
 */
public class TigerTest {
    public static void main(String[] args) {

        Tiger lostTiger = new Tiger(1.5, 300);
        System.out.println("丢失老虎：" + lostTiger);
        try {
            Tiger clonedTiger = (Tiger) lostTiger.clone();
            System.out.println("克隆老虎：" + clonedTiger);
            if (lostTiger.equals(clonedTiger)) {
                System.out.println("一致，可通过上级检查");
            } else {
                System.out.println("比对不一致，无法通过上级检查");
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
