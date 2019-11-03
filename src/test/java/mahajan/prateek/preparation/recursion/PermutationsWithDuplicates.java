package mahajan.prateek.preparation.recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by: pramahajan on 10/31/19 12:16 AM GMT+05:30
 */
public class PermutationsWithDuplicates {


    @Test
    // https://www.techiedelight.com/find-binary-strings-can-formed-given-wildcard-pattern/
    public void print_all_permutations_from_string_with_non_unique_characters() {
        String src = "ABC";

        int n = src.length();

        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char i:src.toCharArray()) {
            if (frequencyMap.get(i) == null) {
                frequencyMap.put(i, 1);
            } else {
                frequencyMap.put(i, frequencyMap.get(i) + 1);
            }
        }

        List<String> result = new ArrayList<>();

        permutations("", n, frequencyMap, result);

        System.out.println(result);
    }

    private void permutations(String prefix, int outputCharsRemaining, Map<Character, Integer> frequencyMap, List<String> result) {
        if (outputCharsRemaining == 0) {
            result.add(prefix);
        } else {
            for (char c:frequencyMap.keySet()) {
                int count = frequencyMap.get(c);
                if (count > 0) {
                    frequencyMap.put(c,  count - 1);
                    permutations(prefix + c, outputCharsRemaining-1, frequencyMap, result);
                    frequencyMap.put(c, count);
                }
            }
        }
    }

}
