package com.game.lol;

/**
 * @author jh
 * @project word2
 * @time 2026/1/12
 */
public class GPU {
    private boolean isIndependent; // 是否独立显卡
    private int memory; // 显存(GB)
    public GPU(boolean isIndependent, int memory) {
        this.isIndependent = isIndependent;
        this.memory = memory;
    }
    public boolean isIndependent() {
        return isIndependent;
    }
    public int getMemory() {
        return memory;
    }
}
