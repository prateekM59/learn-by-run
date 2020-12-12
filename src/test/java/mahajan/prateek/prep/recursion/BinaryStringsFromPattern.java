package mahajan.prateek.prep.recursion;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by: pramahajan on 10/23/19 3:13 AM GMT+05:30
 */
public class BinaryStringsFromPattern {


    @Test
    // https://www.techiedelight.com/find-binary-strings-can-formed-given-wildcard-pattern/
    public void print_all_binary_strings_generated_from_wildcard_pattern() {
        String src = "1??0";
        printAllCombinations(src.toCharArray(), src.length()-1);
    }


    private void printAllCombinations(char pattern[], int i)
    {
        if (i > 0) {
            //System.out.println("i: " + i + Arrays.toString(pattern));
        } else if (i==0) {
            System.out.println("i: " + i + Arrays.toString(pattern));
            return;
        }

        if (pattern[i] == '?') {
            pattern[i] = '0';
            printAllCombinations(pattern, i - 1);

            pattern[i] = '1';
            printAllCombinations(pattern, i - 1);

            pattern[i] = '?'; // revert for further recursion
        } else {
            printAllCombinations(pattern, i - 1);
        }
    }
}
