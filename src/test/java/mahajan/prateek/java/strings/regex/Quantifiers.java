package mahajan.prateek.java.strings.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: pramahajan on 3/26/20 1:49 AM GMT+05:30
 */

// http://tutorials.jenkov.com/java-regex/index.html#quantifiers
// http://tutorials.jenkov.com/java-regex/syntax.html#quantifiers

// * - for zero or more
// + - for one or more
// ? - for zero or one
// {n} - n times
// {n,} - atleast n times
// {n,m} - range n to m (inclusive, inclusive) times


public class Quantifiers {
    @Test
    public void basic_quantifiers_zero_or_more() {
        Pattern pattern = Pattern.compile("hello*"); // hell, hello, helloo, helloooo, etc.

        String input1 = "hello";
        Matcher matcher1 = pattern.matcher(input1);

        print(matcher1, "matcher1");

        String input2 = "hellooo";
        Matcher matcher2 = pattern.matcher(input2);

        print(matcher2, "matcher2");

        String input3 = "hell"; // even matches zero
        Matcher matcher3 = pattern.matcher(input3);

        print(matcher3, "matcher3");
    }

    @Test
    public void basic_quantifiers_one_or_more() {
        Pattern pattern = Pattern.compile("hello+"); // hello, helloo, helloooo, etc.

        String input1 = "hello";
        Matcher matcher1 = pattern.matcher(input1);

        print(matcher1, "matcher1");

        String input2 = "hellooo";
        Matcher matcher2 = pattern.matcher(input2);

        print(matcher2, "matcher2");

        String input3 = "hell"; // does not match zero
        Matcher matcher3 = pattern.matcher(input3);

        print(matcher3, "matcher3");
    }

    @Test
    public void basic_quantifiers_zero_or_one() {
        Pattern pattern = Pattern.compile("hello?"); // hello or hell only.

        String input1 = "hello";
        Matcher matcher1 = pattern.matcher(input1);

        print(matcher1, "matcher1");

        String input2 = "hellooo"; // does not match more than 1
        Matcher matcher2 = pattern.matcher(input2);

        print(matcher2, "matcher2");

        String input3 = "hell";
        Matcher matcher3 = pattern.matcher(input3);

        print(matcher3, "matcher3");
    }

    @Test
    public void n_quantifiers_fixed() {
        Pattern pattern = Pattern.compile("hello{3}"); //hellooo only

        String input1 = "hello"; // does not match
        Matcher matcher1 = pattern.matcher(input1);

        print(matcher1, "matcher1");

        String input2 = "hellooo"; // match
        Matcher matcher2 = pattern.matcher(input2);

        print(matcher2, "matcher2");

        String input3 = "hell"; // does not match
        Matcher matcher3 = pattern.matcher(input3);

        print(matcher3, "matcher3");
    }

    @Test
    public void n_quantifiers_atleast() {
        Pattern pattern = Pattern.compile("hello{2,}"); //helloo, hellooo, helloooo etc

        String input1 = "hello"; // does not match
        Matcher matcher1 = pattern.matcher(input1);

        print(matcher1, "matcher1");

        String input2 = "hellooo"; // match
        Matcher matcher2 = pattern.matcher(input2);

        print(matcher2, "matcher2");

        String input3 = "hell"; // does not match
        Matcher matcher3 = pattern.matcher(input3);

        print(matcher3, "matcher3");

        String input4 = "helloo"; // match
        Matcher matcher4 = pattern.matcher(input4);

        print(matcher4, "matcher4");
    }

    @Test
    public void n_quantifiers_range() {
        Pattern pattern = Pattern.compile("hello{1,3}"); //hello, helloo, hellooo only

        String input1 = "hello"; // match
        Matcher matcher1 = pattern.matcher(input1);

        print(matcher1, "matcher1");

        String input2 = "hellooo"; // match
        Matcher matcher2 = pattern.matcher(input2);

        print(matcher2, "matcher2");

        String input3 = "hell"; // does not match
        Matcher matcher3 = pattern.matcher(input3);

        print(matcher3, "matcher3");

        String input4 = "helloo"; // match
        Matcher matcher4 = pattern.matcher(input4);

        print(matcher4, "matcher4");

        String input5 = "helloooo"; // does not match
        Matcher matcher5 = pattern.matcher(input5);

        print(matcher5, "matcher5");
    }

    @Test
    // Any quantifier (not just *) can be made reluctant by adding ? - check link for details
    // http://tutorials.jenkov.com/java-regex/syntax.html#quantifiers
    public void zero_or_more_quantifier_reluctant_and_greedy() {
        Pattern pattern1 = Pattern.compile("hello*?"); // Adding ? makes reluctant quantifier - will match only what is minimum match

        String input1 = "1hellooo2"; // only matches hell here (min match i.e. zero)
        Matcher matcher1 = pattern1.matcher(input1);

        while(matcher1.find()) {
            System.out.println("Start: " + matcher1.start() + "\tend: " + matcher1.end());
        }

        Pattern pattern2 = Pattern.compile("hello*"); // This is greedy quantifier - will match maximum o(s)

        String input2 = "1hellooo2"; // matches hellooo here i.e. maximum match
        Matcher matcher2 = pattern2.matcher(input2);

        while(matcher2.find()) {
            System.out.println("Start: " + matcher2.start() + "\tend: " + matcher2.end());
        }
    }

    private void print(Matcher matcher, String matcherName) {
        if (matcher.matches()) {
            System.out.println("Matches " + matcherName);
        } else {
            System.out.println("Not Matches " + matcherName);
        }
    }

}
