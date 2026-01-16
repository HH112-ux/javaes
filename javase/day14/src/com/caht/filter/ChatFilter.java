package com.caht.filter;

/**
 * @author jh
 * @project com.caht.filter
 * @time 2026/1/16
 */
public class ChatFilter {
    private String[] sensitiveWords;
    public void setSensitiveWords(String[] sensitiveWords) {
        this.sensitiveWords = sensitiveWords;
    }

    public String filter(String input) {
        StringBuilder sb = new StringBuilder(input);
        for (String word : sensitiveWords) {
            int wordLen = word.length();
            if (wordLen == 0) {
                continue;
            }
            int inputLen = sb.length();
            for (int i = 0; i <= inputLen - wordLen; i++) {
                boolean match = true;
                for (int j = 0; j < wordLen; j++) {
                    if (sb.charAt(i + j) != word.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    for (int k = 0; k < wordLen; k++) {
                        sb.setCharAt(i + k, '*');
                    }
                }
            }
        }
        return sb.toString();
    }
}

