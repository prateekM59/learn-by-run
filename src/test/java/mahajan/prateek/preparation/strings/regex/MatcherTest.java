package mahajan.prateek.preparation.strings.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: pramahajan on 3/21/20 1:58 AM GMT+05:30
 */

// Conclusion - Use full boundary($,^) for regex. Use matches() which is idempotent for full string match.
    // Use find() for finding multiple matches in loop.
public class MatcherTest {
    @Test
    public void matches_without_proper_boundary_regex_AVOID() { // matches() matches if the whole input is matched with pattern's matcher
        Pattern pattern = Pattern.compile("ABC"); // no boundary regex but still works

        String input1 = "ABCABC";
        Matcher matcher1 = pattern.matcher(input1);

        System.out.println(matcher1.matches());  // false because it does not match fully

        String input2 = "ABC";
        Matcher matcher2 = pattern.matcher(input2);

        System.out.println(matcher2.matches());  // true
    }

    @Test
    public void matches_with_proper_boundary_regex() { // matches() matches if the whole input is matched with pattern's matcher
        Pattern pattern = Pattern.compile("^ABC$"); // works same as above

        String input1 = "ABCABC";
        Matcher matcher1 = pattern.matcher(input1);

        System.out.println(matcher1.matches());  // false because it does not match fully

        String input2 = "ABC";
        Matcher matcher2 = pattern.matcher(input2);

        System.out.println(matcher2.matches());  // true
    }

    @Test
    // matches is idempotent and find is not
    // https://stackoverflow.com/a/18409048/8350901
    public void matches_vs_find() { // matches() matches if the whole input is matched with pattern's matcher
        Pattern pattern = Pattern.compile("^ABC$");

        String input1 = "ABC";
        Matcher matcher1 = pattern.matcher(input1);
        Matcher matcher2 = pattern.matcher(input1); // same matcher with 2 different instances

        System.out.println(matcher1.matches());  // true

        System.out.println(matcher1.find());  // false ??? - Because find() searches for the next match from pointer
                                                // and since we have called matches() on the same matcher, the pointer
                                            // has gone ahead and find() returns false

        System.out.println(matcher2.find());  // same matcher but different instance returns true

        System.out.println(matcher1.matches());  // always true because matches() matches full text always
                                                //  and does not depend upon pointer position

    }

    @Test
    public void find_and_reset() { // find() searches for the next match from pointer
        Pattern pattern = Pattern.compile("^ABC$");

        String input1 = "ABC";
        Matcher matcher1 = pattern.matcher(input1);

        System.out.println(matcher1.find());  // true

        System.out.println(matcher1.find());  // false due to pointer gone ahead

        matcher1.reset(); // reset the pointer

        System.out.println(matcher1.find());  // true again
    }
}
