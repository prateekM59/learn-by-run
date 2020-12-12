package mahajan.prateek.prep.recursion;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Stack;

/**
 * Created by: pramahajan on 10/23/19 3:09 AM GMT+05:30
 */

@RunWith(JUnit4.class)

public class NonOverlappingSubstrings {

    @Test
    // https://www.techiedelight.com/find-combinations-non-overlapping-substrings-string/
    public void find_all_non_overlapping_substrings() {
        String src = "ABC";
        overlapSubstring(new Stack<>(), src);
    }


    private void overlapSubstring(Stack<String> res, String str) {
        if (str.length() == 0) {
            System.out.println(res);
            return;
        }
        int n = str.length();
        for (int i=0; i<n;i++) {
            String sub = str.substring(0,i+1);
            //System.out.println("Sub: " + sub);
            res.push(sub);
            overlapSubstring(res, str.substring(i+1, n));
            res.pop();
        }
    }

}
