package com.mrinabh.scrabble;

import java.util.Comparator;

/**
 * Implementation of comparator to help sorting words (Strings) by their
 * Scrabble score
 * 
 * @author mrinabh
 *
 */
public class ScrabbleScoreComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        int score1 = getPoints(o1);
        int score2 = getPoints(o2);

        if (score1 > score2)
            return -1;
        if (score1 < score2)
            return 1;
        
        // if they are equal
        return 0;
    }

    /**
     * 
     * @param word
     * @return score based on the following points values
     * 
     *         <pre>
     *     Points | Letters
     *     -------+-----------------------------
     *        1   | A, E, I, L, N, O, R, S, T, U
     *        2   | D, G
     *        3   | B, C, M, P
     *        4   | F, H, V, W, Y
     *        5   | K
     *        8   | J, X
     *       10   | Q, Z
     *         </pre>
     * 
     *         NOTE: Using switch/case strategy described in this example:
     *         https://www.nku.edu/~sakaguch/msis655/Exam2/Scrabble.java With one
     *         modification: In the example, 'X' has a score 5 but we need it to be
     *         8
     */
    @SuppressWarnings("unused")
    private int getPoints(String word) {
        if (word == null || word.length() == 0)
            return 0;
        word = word.toUpperCase();
        int score = 0;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            switch (c) {
            case 'Q':
            case 'Z':
                score += 10;
                break;

            case 'J':
            case 'X':
                score += 8;
                break;

            case 'K':
                score += 5;
                break;

            case 'F':
            case 'H':
            case 'V':
            case 'W':
            case 'Y':
                score += 4;
                break;

            case 'B':
            case 'C':
            case 'M':
            case 'P':
                score += 3;
                break;

            case 'D':
            case 'G':
                score += 2;
                break;

            default: // For A, E, I, L, N, O, R, S, T, U
                score += 1;
            }
        }
        return score;
    }
}
