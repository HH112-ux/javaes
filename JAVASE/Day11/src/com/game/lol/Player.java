package com.game.lol;

/**
 * @author jh
 * @project word2
 * @time 2026/1/12
 */
public class Player {
    private String name;
    public Player(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void playGame(Computer computer) {
        if (computer.isGameAble()) {
            System.out.println(computer.getBrand()+"电脑成功运行英雄联盟");
        } else {
            System.out.println(computer.getBrand()+ "电脑配置不够无法运行游戏");
        }
    }
}
