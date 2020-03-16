package mahajan.prateek.preparation.strings;

import org.junit.Test;

/**
 * Created by: pramahajan on 3/17/20 2:25 AM GMT+05:30
 */
public class EscapingInString {

    @Test
    // https://stackoverflow.com/questions/1367322/what-are-all-the-escape-characters
    public void escape_backslash_in_string() {
        String testStr = "\\ok";
        System.out.println(testStr);

        // String testStr2 = "\ok";   // ERROR
    }

    @Test
    public void escaping_examples_in_string() {
        String strQuotes = "\"";
        String strTab = "\tabc";
        String strBackspace = "abc\b";

        System.out.println(strQuotes);
        System.out.println(strTab);
        System.out.println(strBackspace);
    }
}
