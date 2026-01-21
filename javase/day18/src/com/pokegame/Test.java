package com.pokegame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.pokegame.PokeRule.*;

/**
 * @author jh
 * @project com.pokegame
 * @time 2026/1/21
 */
public class Test {
    public static void main(String[] args) {
        List<String> pokerDeck = createPokerDeck();
        System.out.println("初始牌组：" + pokerDeck);

        Collections.shuffle(pokerDeck);
        System.out.println("\n洗牌后：" + pokerDeck);

        List<String> LiBai = new ArrayList<>();
        List<String> DuFu = new ArrayList<>();
        List<String> BaiJuYi = new ArrayList<>();
        List<String> landLordCards = new ArrayList<>();
        dealPoker(pokerDeck, LiBai, DuFu, BaiJuYi, landLordCards);

        sortCards(LiBai);
        sortCards(DuFu);
        sortCards(BaiJuYi);

        showCards("李白", LiBai);
        showCards("杜甫", DuFu);
        showCards("白居易", BaiJuYi);
        showCards("底牌", landLordCards);
    }

}
