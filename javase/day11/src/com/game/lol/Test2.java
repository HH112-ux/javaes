package com.game.lol;

/**
 * @author jh
 * @project word2
 * @time 2026/1/12
 */
public class Test2 {
    public static void main(String[] args) {
        GPU card = new GPU(true, 6);
        Computer hp = new Computer("惠普", card);
        Player liBai = new Player("李白");
        liBai.playGame(hp);
    }
}
