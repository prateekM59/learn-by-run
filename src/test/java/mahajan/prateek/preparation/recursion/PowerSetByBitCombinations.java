package mahajan.prateek.preparation.recursion;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by: pramahajan on 10/28/19 5:15 PM GMT+05:30
 */
public class PowerSetByBitCombinations {

    @Test
    // https://www.techiedelight.com/generate-power-set-given-set/
    public void print_all_sub_sets() {
        String src = "ABDC";
        printAllSubsets(src.toCharArray(), src.length());
    }

    private void printAllSubsets(char[] src, int n) {
        ArrayList<ArrayList<Character>> result = new ArrayList<>();

        long max = 1<<n; // 2^n

        for (int num = 0; num < max; num++) {
            ArrayList<Character> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int mask = 1<<i;
                if ((num & mask) != 0) {
                    subset.add(src[i]);
                }
            }

            result.add(subset);
        }

        System.out.println(result);
    }
}
