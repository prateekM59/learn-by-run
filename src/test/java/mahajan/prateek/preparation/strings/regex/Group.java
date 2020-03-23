package mahajan.prateek.preparation.strings.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: pramahajan on 3/23/20 11:56 PM GMT+05:30
 */

// http://tutorials.jenkov.com/java-regex/matcher.html#group-method
// groups are mentioned by () in regex and accessed by matcher.group(index)
public class Group {

    @Test
    public void group_basic() {
        Pattern pattern = Pattern.compile("(\\d+)"); // finding a group of continuous digits, notice there are no boundaries

        String input1 = "apple123mango76bad";
        Matcher matcher1 = pattern.matcher(input1);

        while(matcher1.find()) {
            System.out.println("Group1: " + matcher1.group(1));
        }
    }


    @Test
    // Using index 0 gives entire regex match. Index 1 gives the first group match.
    // If your entire regex is one group only (previous example), group0 and group1 would be same
    // https://stackoverflow.com/a/14484091/8350901 for detail
    public void group_0_index() {
        Pattern pattern = Pattern.compile("(ana)(\\d+)"); // ana is group1, digits are group2 and full regex match is group0

        String input1 = "banana123kibana76apple";
        Matcher matcher1 = pattern.matcher(input1);

        while(matcher1.find()) {
            System.out.println("Group0: " + matcher1.group(0));
            System.out.println("Group1: " + matcher1.group(1));
            System.out.println("Group2: " + matcher1.group(2));
        }
    }

    @Test
    // Nested groups are numbered based on when the left parenthesis of the group is met
    public void nested_groups() {
        // Outer bracket is group1, ana is group2 and digits are group3. Full match is group0.
        Pattern pattern = Pattern.compile("((ana)(\\d+))gone");

        String input1 = "banana123gonekibana76gonelittle";
        Matcher matcher1 = pattern.matcher(input1);

        while(matcher1.find()) {
            System.out.println("Group0: " + matcher1.group(0));
            System.out.println("Group1: " + matcher1.group(1));
            System.out.println("Group2: " + matcher1.group(2));
            System.out.println("Group3: " + matcher1.group(3));
        }
    }

}
