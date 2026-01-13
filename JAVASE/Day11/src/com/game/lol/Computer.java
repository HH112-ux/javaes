package com.game.lol;

/**
 * @author jh
 * @project word2
 * @time 2026/1/12
 */
public class Computer {
    private String brand;
    private GPU crad;
    public Computer(String brand, GPU card) {
        this.brand = brand;
        this.crad = card;
    }
    // 检查显卡是否满足游戏要求
    public boolean isGameAble() {
        return crad.isIndependent() && crad.getMemory() > 4;
    }
    public String getBrand() {
        return brand;
    }
}
