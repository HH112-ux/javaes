package com.pokegame;

import java.util.*;

/**
 * @author jh
 * @project com.pokegame
 * @time 2026/1/21
 */
public class PokeRule {
    private static final Map<String, Integer> COLOUR_RULE = new HashMap<>();
    private static final Map<String, Integer> POKE_RULE = new HashMap<>();

    static {

        COLOUR_RULE.put("黑桃", 4);
        COLOUR_RULE.put("红桃", 3);
        COLOUR_RULE.put("梅花", 2);
        COLOUR_RULE.put("方块", 1);
        POKE_RULE.put("A", 13);
        POKE_RULE.put("K", 12);
        POKE_RULE.put("Q", 11);
        POKE_RULE.put("J", 10);
        POKE_RULE.put("10", 9);
        POKE_RULE.put("9", 8);
        POKE_RULE.put("8", 7);
        POKE_RULE.put("7", 6);
        POKE_RULE.put("6", 5);
        POKE_RULE.put("5", 4);
        POKE_RULE.put("4", 3);
        POKE_RULE.put("3", 2);
        POKE_RULE.put("2", 1);
    }

    protected static List<String> createPokerDeck() {
        List<String> deck = new ArrayList<>();
        deck.add("大王");
        deck.add("小王");
        String[] colors = {"黑桃", "红桃", "梅花", "方块"};
        String[] nums = {"A", "K", "Q", "J", "10", "9", "8", "7", "6", "5", "4", "3", "2"};
        for (String color : colors) {
            for (String num : nums) {
                deck.add(color + num);
            }
        }
        return deck;
    }

    protected static void dealPoker(List<String> deck, List<String> p1, List<String> p2, List<String> p3, List<String> landLord) {
        for (int i = 0; i < deck.size(); i++) {
            String card = deck.get(i);
            if (i >= deck.size() - 3) {
                landLord.add(card);
            } else {

                if (i % 3 == 0) {
                    p1.add(card);
                } else if (i % 3 == 1) {
                    p2.add(card);
                } else {
                    p3.add(card);
                }
            }
        }
    }

    protected static void sortCards(List<String> cards) {
        Collections.sort(cards,
                new Comparator<String>() {
                    @Override
                    public int compare(String card1, String card2) {

                        if (card1.equals("大王")) return -1;
                        if (card2.equals("大王")) return 1;
                        if (card1.equals("小王")) return -1;
                        if (card2.equals("小王")) return 1;

                        String color1 = card1.substring(0, 2);
                        String num1 = card1.substring(2);
                        String color2 = card2.substring(0, 2);
                        String num2 = card2.substring(2);

                        int colorCompare = COLOUR_RULE.get(color2) - COLOUR_RULE.get(color1);
                        if (colorCompare != 0) {
                            return colorCompare;
                        }
                        return POKE_RULE.get(num2) - POKE_RULE.get(num1);
                    }
                });
    }

    protected static void showCards(String playerName, List<String> cards) {
        System.out.println(playerName + cards);
    }
}

