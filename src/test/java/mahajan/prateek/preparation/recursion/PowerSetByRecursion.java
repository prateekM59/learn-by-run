package mahajan.prateek.preparation.recursion;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by: pramahajan on 10/28/19 5:53 PM GMT+05:30
 */
public class PowerSetByRecursion {

    @Test
    // https://www.techiedelight.com/generate-power-set-given-set/
    public void print_all_sub_sets() {
        String src = "ABC";
        ArrayList<ArrayList<Character>> subsets = getSubsets(src.toCharArray(), src.length());
        System.out.println(subsets);
    }


    //  T(0) = {},
    // T{3} = T{2} + (clone of T(2) with a[3] added to each element)
    // Refer CITC
    private ArrayList<ArrayList<Character>> getSubsets(char[] src, int n) {
        if (n == 0) {
            ArrayList<Character> empty = new ArrayList<>();
            ArrayList<ArrayList<Character>> result = new ArrayList<>();
            result.add(empty);
            return result;
        }

        ArrayList<ArrayList<Character>> prev = getSubsets(src, n - 1);
        ArrayList<ArrayList<Character>> curr = new ArrayList<>();

        for (ArrayList<Character> arr : prev) {
            ArrayList<Character> copy = new ArrayList<>(arr);
            copy.add((src[n-1])); // adding item src[n-1] i.e. nth item
            curr.add(copy);
        }

        ArrayList<ArrayList<Character>> result = new ArrayList<>();

        result.addAll(prev);
        result.addAll(curr);

        return result;
    }
}
