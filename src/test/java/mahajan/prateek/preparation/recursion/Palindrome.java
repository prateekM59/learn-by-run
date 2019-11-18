package mahajan.prateek.preparation.recursion;

import org.junit.Test;

/**
 * Created by: pramahajan on 11/19/19 1:30 AM GMT+05:30
 */
public class Palindrome {

    @Test
    public void is_palindrome() {
        String src = "ABCCCBAA";
        System.out.println(isPalindrome(src, 0, src.length()-1));
    }


    private boolean isPalindrome(String src, int i, int j) {
        if (i<=j) {
            if (src.charAt(i) == src.charAt(j)) {
                return isPalindrome(src, i+1, j-1);
            }
            return false;
        }

        return true;
    }
}
