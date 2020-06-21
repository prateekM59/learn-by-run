package mahajan.prateek.preparation.recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by: pramahajan on 6/21/20 6:53 PM GMT+05:30
 */
public class PalindromePermutation2 {
    public List<String> generatePalindromes(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (char ch: s.toCharArray()) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch)+1);
            } else {
                map.put(ch, 1);
            }
        }

        int oddCount = 0;
        char oddCh = '0';

        for (Map.Entry<Character, Integer> entry: map.entrySet()) {
            if (entry.getValue()%2 == 1) {
                oddCount++;

                if (oddCount>1) {
                    return new ArrayList<>();
                }

                oddCh = entry.getKey();
            } else {
                map.put(entry.getKey(), entry.getValue()/2);
            }
        }


        if (oddCh != '0') {
            map.put(oddCh, (map.get(oddCh)-1)/2);
        }

        List<String> ans = new ArrayList<>();

        P(new StringBuilder(), ans, map, oddCh);

        return ans;
    }

    private void P(StringBuilder sb, List<String> ans, Map<Character, Integer> map, char oddCh) {
        boolean found = true;
        for (int i: map.values()) {
            if (i>0) {
                found = false;
                break;
            }
        }

        if (found) {
            String sFirst = sb.toString();
            StringBuilder sbLast = new StringBuilder(sFirst); // make copy to reverse, don't mess with sb
            String sLast = sbLast.reverse().toString();
            String s;
            if (oddCh != '0') {
                s = sFirst + oddCh + sLast;
            } else {
                s = sFirst + sLast;
            }

            ans.add(s);
            return;
        }

        for (char ch: map.keySet()) {
            int count = map.get(ch);
            if (count>0) {
                sb.append(ch);
                map.put(ch, count-1);

                P(sb, ans, map, oddCh);

                sb.deleteCharAt(sb.length()-1);
                map.put(ch, count);
            }
        }

    }

    @Test
    public void test1() {
        System.out.println(generatePalindromes("aabbdd"));
    }

}
