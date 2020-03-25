package mahajan.prateek.java.strings.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: pramahajan on 3/24/20 1:39 AM GMT+05:30
 */
// http://tutorials.jenkov.com/java-regex/syntax.html

public class CharacterClasses {

    @Test
    public void characters() {
        Pattern pattern = Pattern.compile("ABC32");

        String input1 = "vABC32a1ABC32a2";
        Matcher matcher1 = pattern.matcher(input1);

        while(matcher1.find()) {
            System.out.println("Start: " + matcher1.start() + "\tend: " + matcher1.end());
        }
    }

    @Test
    // . is to match with any single character
    public void any_character() {
        Pattern pattern = Pattern.compile("ABC.");

        String input1 = "ABCD";
        Matcher matcher1 = pattern.matcher(input1);

        if (matcher1.matches()) {
            System.out.println("matches matcher1");
        } else {
            System.out.println("does not match matcher1");
        }

        String input2 = "ABC1";
        Matcher matcher2 = pattern.matcher(input2);

        if (matcher2.matches()) {
            System.out.println("matches matcher2");
        } else {
            System.out.println("does not match matcher2");
        }
    }

    @Test
    // character classes are mentioned by []
    public void character_class() {
        Pattern pattern = Pattern.compile("[ab]"); // matches either a or b

        String input1 = "14a4cb33C1";
        Matcher matcher1 = pattern.matcher(input1);

        while(matcher1.find()) {
            System.out.println("Start: " + matcher1.start() + "\tend: " + matcher1.end());
        }
    }

    @Test
    public void character_class_range() {
        Pattern pattern = Pattern.compile("[a-z]"); // matches one character from a-z (case sensitive)

        String input1 = "14a4cb33C1";
        Matcher matcher1 = pattern.matcher(input1);

        while(matcher1.find()) {
            System.out.println("Start: " + matcher1.start() + "\tend: " + matcher1.end());
        }
    }

    @Test
    public void character_class_multiple_ranges() {
        Pattern pattern = Pattern.compile("[a-zA-Z]"); // matches one character from a-z or from A-Z, basically alphabets

        String input1 = "14a4cb33C1";
        Matcher matcher1 = pattern.matcher(input1);

        while(matcher1.find()) {
            System.out.println("Start: " + matcher1.start() + "\tend: " + matcher1.end());
        }
    }

    @Test
    // ^ inside character class indicates negation
    public void character_class_negation() {
        Pattern pattern = Pattern.compile("[^ab]"); // match anything other than a or b

        String input1 = "14a4cb33C1";
        Matcher matcher1 = pattern.matcher(input1);

        while(matcher1.find()) {
            System.out.println("Start: " + matcher1.start() + "\tend: " + matcher1.end());
        }
    }

    @Test
    // && is used for intersection in character classes
    public void character_class_intersection() {
        Pattern pattern = Pattern.compile("[a-z&&[bc]]"); // common of a-z and bc i.e. bc

        String input1 = "14a4cb33C1";
        Matcher matcher1 = pattern.matcher(input1);

        while(matcher1.find()) {
            System.out.println("Start: " + matcher1.start() + "\tend: " + matcher1.end());
        }
    }


    @Test
    // && along with ^ is used for range subtraction in character classes
    public void character_class_range_subtraction() {
        Pattern pattern = Pattern.compile("[a-z&&[^bc]]"); // a-z and not of bc

        String input1 = "14a4cb33C1"; // will just match a
        Matcher matcher1 = pattern.matcher(input1);

        while(matcher1.find()) {
            System.out.println("Start: " + matcher1.start() + "\tend: " + matcher1.end());
        }
    }

    @Test
    public void character_class_union() {
        Pattern pattern = Pattern.compile("[a-c[A-C]]"); // either a-c or A-C

        String input1 = "14a4cbd33CD1"; // will just match a
        Matcher matcher1 = pattern.matcher(input1);

        while(matcher1.find()) {
            System.out.println("Start: " + matcher1.start() + "\tend: " + matcher1.end());
        }
    }

    @Test
    // \d for digits
    // \w for any word character(a-z, A-Z, 0-9 and _)
    // \s for whitespace (space, tabs, newlines, etc.)
    public void predefined_classes() {
        // note that \t represents tab as per java compiler
        // no need to \\ as it is input string and not pattern
        // only patterns need to have \\ because of double compiling - first by javac and then by Pattern.compile()
        String input = "a1&_ w@\t";


        Pattern pattern1 = Pattern.compile("\\d"); // any digit
        Matcher matcher1 = pattern1.matcher(input);

        while(matcher1.find()) {
            System.out.println("\\d Start: " + matcher1.start() + "\tend: " + matcher1.end());
        }

        Pattern pattern2 = Pattern.compile("\\w"); // any alphanumeric character (and _ symbol also)
        Matcher matcher2 = pattern2.matcher(input);

        while(matcher2.find()) {
            System.out.println("\\w Start: " + matcher2.start() + "\tend: " + matcher2.end());
        }

        Pattern pattern3 = Pattern.compile("\\s"); // any whitespace character (and _ symbol also)
        Matcher matcher3 = pattern3.matcher(input);

        while(matcher3.find()) {
            System.out.println("\\s Start: " + matcher3.start() + "\tend: " + matcher3.end());
        }
    }

}
