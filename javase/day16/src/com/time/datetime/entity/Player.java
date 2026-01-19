package com.time.datetime.entity;

import java.time.LocalDate;

/**
 * @author jh
 * @project com.time.datetime.entity
 * @time 2026/1/19
 */
public class Player {
    private String name;
    private LocalDate buyDate;
    private boolean buySuccess;
    private double cost;
    private static final int ORIGINAL_PRICE = 1788;
    private static final LocalDate LAUNCH_DATE = LocalDate.of(2026, 9, 2);

    public Player(String name, LocalDate buyDate) {
        this.name = name;
        this.buyDate = buyDate;
        this.calculatePrice();
    }

    private void calculatePrice() {
        long daysAfterLaunch = java.time.temporal.ChronoUnit.DAYS.between(LAUNCH_DATE, buyDate);
        if (daysAfterLaunch < 0 || daysAfterLaunch >= 28) {
            this.buySuccess = false;
            this.cost = 0;
            return;
        }
        this.buySuccess = true;
        if (daysAfterLaunch < 7) {

            this.cost = ORIGINAL_PRICE * 0.8;
        } else if (daysAfterLaunch < 14) {

            this.cost = ORIGINAL_PRICE * 0.9;
        } else {

            this.cost = ORIGINAL_PRICE;
        }
    }

    @Override
    public String toString() {
        return String.format(
                "玩家%s 购买时间%s 是否成功%s 花费点券%.0f",
                name, buyDate, buySuccess ? "是" : "否", cost
        );
    }
}

