package mahajan.prateek;

import java.util.Map;
import java.util.TreeMap;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Map<String, Integer> map = new TreeMap<>();
        map.put(null, 22);
    }

    private static int doStuff(String[] args) {
        return Integer.parseInt(args[0]);
    }
}
