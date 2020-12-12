package mahajan.prateek.prep.recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: pramahajan on 5/13/20 3:18 AM GMT+05:30
 */
// https://leetcode.com/problems/different-ways-to-add-parentheses/submissions/
public class DiffWaysToAddParantheses {

    @Test
    public void different_ways_to_add_parentheses() {
        System.out.println(P("2-1-1"));
    }


    public List<Integer> P(String s) {
        int n = s.length();
        List<Integer> list = new ArrayList<>();

        for(int i=1;i<n;i++) {
            if (isOperator(s.charAt(i))) {
                String first = s.substring(0,i);
                List<Integer> firstList = P(first);
                String second = s.substring(i+1);
                List<Integer> secondList = P(second);

                for (Integer fir:firstList) {
                    for (Integer sec:secondList) {
                        list.add(operate(fir,sec,s.charAt(i)));
                    }
                }
            }
        }

        if (list.size()==0) { // there is no operator
            list.add(Integer.valueOf(s));
        }

        return list;


    }

    private boolean isOperator(char ch) {
        return ch=='-' || ch=='+' || ch=='*';
    }

    private int operate(int f, int s, char ch) {
        switch(ch) {
        case '+':
            return f+s;
        case '-':
            return f-s;
        default:
            return f*s;
        }
    }
}
