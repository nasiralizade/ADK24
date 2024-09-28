/* Labb 2 i DD2350 Algoritmer, datastrukturer och komplexitet    */
/* Se labbinstruktionerna i kursrummet i Canvas                  */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */

import java.util.ArrayList;
import java.util.List;

public class ClosestWords {
    int closestDistance = Integer.MAX_VALUE;
    int[][] d;
    String prev_word = "";
    List<String> closestWords = new ArrayList<>();

    /**
     * Fills the first row and column of the matrix with the base cases
     * @param w The word to compare with
     */
    public void fill_base_cases(String w) {
        d = new int[50][w.length() + 1]; // 50 is the maximum length of a word in the word list

        //  first row
        for (int i = 0; i < d.length; i++) {
            d[i][0] = i;
        }
        // first column
        for (int j = 0; j <= w.length(); j++) {
            d[0][j] = j;
        }
    }
    /**
     * Constructor for the ClosestWords class
     * @param w1 The word to compare with
     * @param wordList The list of words to compare with
     */
    public ClosestWords(String w1, List<String> wordList) {
        fill_base_cases(w1);

        for (String s : wordList) {
            if (Math.abs(s.length() - w1.length()) > closestDistance) {
                continue; // Skip if the length difference is already greater than the closest found
            }

            int dist = distance(w1, s);

            if (dist < closestDistance) {
                closestDistance = dist;
                closestWords.clear();
                closestWords.add(s);
            } else if (dist == closestDistance) {
                closestWords.add(s);
            }
        }
    }


    /**
     * Calculates the Levenshtein distance between two words
     * @param w1 The first word
     * @param wordToMatchWith The second word
     * @return The Levenshtein distance between the two words
     */
    int distance(String w1,String wordToMatchWith ) {
        int wordToMatchWithLength = wordToMatchWith.length();
        int w1_Length = w1.length();

        int cp = commonPrefix(prev_word, wordToMatchWith);

        for (int i = cp + 1; i <= wordToMatchWithLength; i++) {
            for (int j = 1; j <= w1_Length; j++) {
                int substitute = d[i - 1][j - 1]
                        + (wordToMatchWith.charAt(i - 1) != w1.charAt(j - 1) ? 1 : 0);
                int insert = d[i][j - 1] + 1;
                int delete = d[i - 1][j] + 1;
                d[i][j] = Math.min(Math.min(substitute, insert), delete);
            }
        }

        prev_word = wordToMatchWith;
        return d[wordToMatchWithLength][w1_Length];
    }

    /**
     * Finds the common prefix of two words
     * @param w1 The first word
     * @param w2 The second word
     * @return The length of the common prefix
     */
    int commonPrefix(String w1, String w2) {
        int minLength = Math.min(w1.length(), w2.length());
        for (int i = 0; i < minLength; i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                return i;
            }
        }
        return minLength;
    }

    public int getMinDistance() {
        return closestDistance;
    }

    public List<String> getClosestWords() {
        return closestWords;
    }
}