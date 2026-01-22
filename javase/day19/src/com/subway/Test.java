package com.subway;

import static com.subway.SubwayFeeSystem.calculateFee;

/**
 * @author jh
 * @project com.subway
 * @time 2026/1/22
 */
public class Test {
    public static void main(String[] args) {
        int liBaiFee = calculateFee("凤凰山站", "万寿路站");
        System.out.println("李白的车票价格：" + liBaiFee + "元");
        int duFuFee = calculateFee("河滨公园站", "南京工业大学站");
        System.out.println("杜甫的车票价格：" + duFuFee + "元");
    }
}
