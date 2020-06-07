package mahajan.prateek.java.strings.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: pramahajan on 3/17/20 3:11 AM GMT+05:30
 */

// Escape characters
// <            => use as \\<
// >            => use as \\>
// (
// )
// [
// ]
// {
// }
// \
// ^
// -
// =
// $
// !
// |
// ?
// *
// +
// .

//http://tutorials.jenkov.com/java-regex/index.html
public class Basic {
    @Test
    public void use_basic_matcher_and_pattern() {
        Pattern regex = Pattern.compile("ABC");

        String input1 = "ooABCoABC";
        Matcher matcher1 = regex.matcher(input1);

        String input2 = "ooo";
        Matcher matcher2 = regex.matcher(input2);

        System.out.println(matcher1.find()); // true
        System.out.println(matcher2.find()); // false
    }

    @Test
    // start() gives the starting position (inclusive) , end() gives the ending position (exclusive)
    public void using_find_to_get_the_matches_position() {
        Pattern regex = Pattern.compile("ABC");

        String input1 = "ooABCoABCpp";
        Matcher matcher1 = regex.matcher(input1);

        while (matcher1.find()) {
            System.out.println("Found match at: " + matcher1.start() + " to " + (matcher1.end()-1));
        }
    }
}
