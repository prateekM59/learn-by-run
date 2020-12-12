package mahajan.prateek.prep.dp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by: pramahajan on 4/23/20 3:15 AM GMT+05:30
 */

// https://leetcode.com/problems/word-break-ii/
public class WordBreak2 {
    boolean[][] isBreakable;

    @Test
    public void wordBreak() {
        List<String> list = wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"));
        System.out.println(list);
    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);

        int n = s.length();
        isBreakable = new boolean[n][n];

        // O(n^3) preprocessing - we need easy access to check if s[i..j] is breakable or not -similar to isPalidrome(i..j) optimization - refer copy
        for (int L=0;L<=n-1;L++) {
            for (int i=0;i<=n-L-1;i++) {
                int j=L+i;

                if (set.contains(s.substring(i, j+1))) {
                    isBreakable[i][j] = true;
                } else {
                    for (int k=i;k<=j-1;k++) {
                        if (isBreakable[i][k] && isBreakable[k+1][j]) {
                            isBreakable[i][j] = true;
                            break;
                        }
                    }
                }

            }
        }

        List<String> list = new ArrayList<>();
        P(s, "", list, set, 0, s.length()-1);

        return list;


    }

    private void P(String s, String res, List<String> list , Set<String> set, int sOffset, int N) { // sOffset is absolute starting index of current substring s in original string and N is last index of original string - these are needed for isBreakable table


        int n = s.length();

        if (n == 0 && res.length() != 0) {
            list.add(res);
        }

        for (int i=0;i<=n-1;i++) {
            String startingWord = s.substring(0, i+1);  // substring from 0 to i
            int secondWordAbsIndex = sOffset+i+1; // next part of s from i+1 to N

            // only recurse if first part is a word and second part is breakable
            // secondWordAbsIndex>=N is for when whole s is word so secondWordAbsIndex would be N+1
            // this check not only recurses in this case as well with "" s
            // but also avoids IndexOutOfBounds for isBreakable matrix
            if (set.contains(startingWord) && (secondWordAbsIndex>=N || isBreakable[secondWordAbsIndex][N])) {
                startingWord = res.length() == 0 ? startingWord : (" " + startingWord);
                P(s.substring(i+1), res+startingWord, list, set, secondWordAbsIndex, N);
            }
        }


    }

}
