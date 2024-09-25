/* Labb 2 i DD2350 Algoritmer, datastrukturer och komplexitet    */
/* Se labbinstruktionerna i kursrummet i Canvas                  */
/* Ursprunglig författare: Viggo Kann KTH viggo@nada.kth.se      */


import java.util.*;
import java.util.List;

public class ClosestWords {
    List<String> closestWords = null;
    int closestDistance = -1;

/*
    int partDist(String w1, String w2, int w1len, int w2len) {

        int[][] d = new int[w1len + 1][w2len + 1];
        for (int i = 0; i <= w1len; i++) {
            d[i][0] = i;
        }
        for (int j = 0; j <= w2len; j++) {
            d[0][j] = j;
        }
        for (int i = 1; i <= w1len; i++) {
            for (int j = 1; j <= w2len; j++) {
                int cost = w1.charAt(i - 1) == w2.charAt(j - 1) ? 0 : 1;
                d[i][j] = Math.min(d[i - 1][j - 1] + cost,
                        Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1));
            }
        }
        return d[w1len][w2len];
    }
    */
int partDist(String w1, String w2, int w1len, int w2len) {

    if (w1len > w2len) {
        return partDist(w2, w1, w2len, w1len);
    }

    int[] prev = new int[w1len + 1];
    int[] curr = new int[w1len + 1];

    for (int i = 0; i <= w1len; i++) {
        prev[i] = i;
    }

    for (int j = 1; j <= w2len; j++) {
        curr[0] = j;
        for (int i = 1; i <= w1len; i++) {
            int cost = (w1.charAt(i - 1) == w2.charAt(j - 1)) ? 0 : 1;
            curr[i] = Math.min(Math.min(curr[i - 1] + 1, prev[i] + 1), prev[i - 1] + cost);
        }
        // Swap prev and curr arrays
        int[] temp = prev;
        prev = curr;
        curr = temp;
    }
    return prev[w1len];
}
    /*int distance(String w1, String w2) {
      return partDist(w1, w2, w1.length(), w2.length());
    }*/

    int distance(String w1, String w2) {
        int lenDiff = Math.abs(w1.length() - w2.length());
        if (lenDiff > closestDistance && closestDistance != -1) {
            return Integer.MAX_VALUE;
        }

        return partDist(w1, w2, w1.length(), w2.length());
    }

    public ClosestWords(String w, List<String> wordList) {
        closestWords = new ArrayList<>();
        for (String s : wordList) {
            int dist = distance(w, s);
            if (dist < closestDistance || closestDistance == -1) {
                closestDistance = dist;
                closestWords.clear();
                closestWords.add(s);
            } else if (dist == closestDistance) {
                closestWords.add(s);
            }
            if (dist == 0) {
                break;
            }
        }
    }

    int getMinDistance() {
        return closestDistance;
    }

    List<String> getClosestWords() {
        return closestWords;
    }
}
