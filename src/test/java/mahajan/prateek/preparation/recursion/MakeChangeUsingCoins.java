package mahajan.prateek.preparation.recursion;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by: pramahajan on 11/2/19 2:05 AM GMT+05:30
 */
public class MakeChangeUsingCoins {

    @Test
    // https://www.geeksforgeeks.org/print-all-combinations-of-balanced-parentheses/
    public void count_ways_of_making_change_using_coins() {
        int arr[] = {3, 2, 1};
        int m = arr.length;
        System.out.println( count(arr, m, 5));
    }

    private int makeChange(int amount, int[] denoms, int index) {
        if (index >= denoms.length - 1) return 1;

        int denomAmount = denoms[index];

        int ways = 0;

        for (int i =0; i * denomAmount <=amount; i++) {
            int amountRemaining = amount - i * denomAmount;
            ways += makeChange(amountRemaining, denoms, index + 1);
        }

        return ways;
    }

    int count( int S[], int m, int n )
    {
        // If n is 0 then there is 1 solution
        // (do not include any coin)
        if (n == 0)
            return 1;

        // If n is less than 0 then no
        // solution exists
        if (n < 0)
            return 0;

        // If there are no coins and n
        // is greater than 0, then no
        // solution exist
        if (m <=0 && n >= 1)
            return 0;

        // count is sum of solutions (i)
        // including S[m-1] (ii) excluding S[m-1]
        return count( S, m - 1, n ) +
                count( S, m, n-S[m-1] );
    }

}
