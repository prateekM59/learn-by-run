package mahajan.prateek.prep.recursion;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by: pramahajan on 11/2/19 12:32 AM GMT+05:30
 */
public class AddParenthesis {

    @Test
    // https://www.geeksforgeeks.org/print-all-combinations-of-balanced-parentheses/
    public void print_all_combinations_of_balanced_parentheses() {
        int count = 2; // number of left and right brackets each

        char [] str = new char[count*2];

        ArrayList<String> list = new ArrayList<>();

        addParen(list, count, count, str, 0);

        System.out.println(list);

    }

    private void addParen(ArrayList<String> list, int leftRem, int rightRem, char[] str, int count) {
        if (leftRem < 0 || rightRem < leftRem)  return;

        if (leftRem == 0 && rightRem == 0) {
            String s = new String(str);
            list.add(s);
        } else { // each recursion adds either ( or ) at index count and then recurses again
            if (leftRem > 0) {
                str[count] = '(';
                addParen(list, leftRem - 1 , rightRem, str, count + 1);
            }

            str[count] = 0; //backtrack, decimal 0 is equivalent to unicode '\u0000' which is default value of a char, printed as a square

            if (rightRem - leftRem > 0) {
                str[count] = ')';
                addParen(list, leftRem, rightRem - 1, str, count + 1);
            }

            str[count] = 0; //backtrack - surprisingly, this works without any of 2 backtracks as well. Refer CTCI P280.
        }
    }

}
