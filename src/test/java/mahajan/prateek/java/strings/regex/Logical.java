package mahajan.prateek.java.strings.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: pramahajan on 3/26/20 3:10 AM GMT+05:30
 */

// By default, all regex matchers are in Logical AND
// Use | for Logical OR

//http://tutorials.jenkov.com/java-regex/index.html#logical-operators

public class Logical {

    @Test
    public void logical_and_default() {
        Pattern pattern = Pattern.compile("[aA][bC]"); // ab or aC or Ab or AC

        String input1 = "ab"; // match
        Matcher matcher1 = pattern.matcher(input1);

        print(matcher1, "matcher1");

        String input2 = "aC"; // match
        Matcher matcher2 = pattern.matcher(input2);

        print(matcher2, "matcher2");

        String input3 = "Ab"; // match
        Matcher matcher3 = pattern.matcher(input3);

        print(matcher3, "matcher3");

        String input4 = "AC"; // match
        Matcher matcher4 = pattern.matcher(input4);

        print(matcher4, "matcher4");

        String input5 = "bC"; // no match
        Matcher matcher5 = pattern.matcher(input5);

        print(matcher5, "matcher5");
    }

    @Test
    public void logical_or_1() {
        Pattern pattern = Pattern.compile("[aA]|[bC]"); // a or A or b or C

        String input1 = "a"; // match
        Matcher matcher1 = pattern.matcher(input1);

        print(matcher1, "matcher1");

        String input2 = "A"; // match
        Matcher matcher2 = pattern.matcher(input2);

        print(matcher2, "matcher2");

        String input3 = "b"; // match
        Matcher matcher3 = pattern.matcher(input3);

        print(matcher3, "matcher3");

        String input4 = "C"; // match
        Matcher matcher4 = pattern.matcher(input4);

        print(matcher4, "matcher4");

        String input5 = "ab"; // no match
        Matcher matcher5 = pattern.matcher(input5);

        print(matcher5, "matcher5");
    }

    @Test
    public void logical_or_2() {
        Pattern pattern = Pattern.compile(".*Ariel.*|.*Sleeping Beauty.*"); // either contains Ariel or Sleeping Beauty

        String text1 = "Cindarella and Sleeping Beauty sat in a tree"; // match
        Matcher matcher1 = pattern.matcher(text1);

        print(matcher1, "matcher1");

        String text2 = "Ariel sat in a tree"; // match
        Matcher matcher2 = pattern.matcher(text2);

        print(matcher2, "matcher2");

        String text3 = "Ariel and Sleeping Beauty sat in a tree"; // match
        Matcher matcher3 = pattern.matcher(text3);

        print(matcher3, "matcher3");

        String text4 = "Sleeping sat in a tree"; // no match
        Matcher matcher4 = pattern.matcher(text4);

        print(matcher4, "matcher4");
    }

    private void print(Matcher matcher, String matcherName) {
        if (matcher.matches()) {
            System.out.println("Matches " + matcherName);
        } else {
            System.out.println("Not Matches " + matcherName);
        }
    }

}
