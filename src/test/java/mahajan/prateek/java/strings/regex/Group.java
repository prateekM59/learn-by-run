package mahajan.prateek.java.strings.regex;

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

    @Test
    public void grouping_with_logical_or() {
        // Groups around logical or are numbered in order
        // There are 5 groups in this expression
        Pattern pattern = Pattern.compile("^(.*)_(.*)@(.*)|(.*)@(.*)$");

        String input1 = "email_one@leetcode.com";  // matches left condition of logical or => generates 3 groups
        Matcher matcher1 = pattern.matcher(input1);

        while(matcher1.find()) {
            System.out.println("Matcher1 Group0: " + matcher1.group(0));
            System.out.println("Matcher1 Group1: " + matcher1.group(1));
            System.out.println("Matcher1 Group2: " + matcher1.group(2));
            System.out.println("Matcher1 Group3: " + matcher1.group(3));
            System.out.println("Matcher1 Group4: " + matcher1.group(4));
            System.out.println("Matcher1 Group5: " + matcher1.group(5));
        }

        String input2 = "emailtwo@leetcode.com";  // matches right condition of logical or => generates 2 groups
        Matcher matcher2 = pattern.matcher(input2);

        while(matcher2.find()) {
            System.out.println("Matcher2 Group0: " + matcher2.group(0));
            System.out.println("Matcher2 Group1: " + matcher2.group(1));
            System.out.println("Matcher2 Group2: " + matcher2.group(2));
            System.out.println("Matcher2 Group3: " + matcher2.group(3));
            System.out.println("Matcher2 Group4: " + matcher2.group(4));
            System.out.println("Matcher2 Group5: " + matcher2.group(5));
        }
    }


}
