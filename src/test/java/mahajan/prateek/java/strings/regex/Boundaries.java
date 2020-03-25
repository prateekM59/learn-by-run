package mahajan.prateek.java.strings.regex;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by: pramahajan on 3/24/20 2:02 AM GMT+05:30
 */

//http://tutorials.jenkov.com/java-regex/index.html#boundaries
public class Boundaries {
    @Test
    public void starting_boundary() {
        String text = "http://jenkov.com";

        Pattern pattern = Pattern.compile("^http://");
        Matcher matcher = pattern.matcher(text);

        while(matcher.find()){
            System.out.println("Found match at: "  + matcher.start() + " to " + matcher.end());
        }
    }

    @Test
    public void ending_boundary() {
        String text = "http://jenkov.com";

        Pattern pattern = Pattern.compile("\\.com$");
        Matcher matcher = pattern.matcher(text);

        while(matcher.find()){
            System.out.println("Found match at: "  + matcher.start() + " to " + matcher.end());
        }
    }

    @Test
    public void full_boundary_match() {
        Pattern pattern = Pattern.compile("^http://.*\\.com$");

        String text1 = "http://jenkov.com";
        Matcher matcher1 = pattern.matcher(text1);

        while(matcher1.find()){
            System.out.println("Found match at: "  + matcher1.start() + " to " + matcher1.end());
        }

        String text2 = "jenkov.com"; // no match
        Matcher matcher2 = pattern.matcher(text2);

        while(matcher2.find()){
            System.out.println("Found match at: "  + matcher2.start() + " to " + matcher2.end());
        }
    }
}
