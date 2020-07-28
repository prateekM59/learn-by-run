package mahajan.prateek.java.inbuilt;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by: pramahajan on 7/29/20 4:12 AM GMT+05:30
 */
// Use TreeMap if you want to have operations (get/put/remove) on ORDERED data and can tolerate logN time
// Use HashMap if you want to have operations (get/put/remove) on UN-ORDERED data (O(1) time)
public class TreeMaps {

    @Test
    public void treeMap_vs_hashMap_iteration() {
        // Balanced BST based
        Map<Integer, String> orderedMap = new TreeMap<>();
        orderedMap.put(2, "VP"); // O(logN)
        orderedMap.put(1, "President");
        orderedMap.put(3, "Dev");

        System.out.println("Ordered by key iteration in TreeMap: ");
        for (Map.Entry<Integer, String> entry: orderedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Array based
        Map<Integer, String> unorderedMap = new HashMap<>();
        unorderedMap.put(20, "VP"); // O(1)
        unorderedMap.put(11, "President");
        unorderedMap.put(33, "Dev");

        System.out.println("Unordered iteration in HashMap: ");
        for (Map.Entry<Integer, String> entry: unorderedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    @Test
    public void treeMap_vs_hashMap_basic_operations() { // works same but time is different
        // Balanced BST based
        Map<Integer, String> orderedMap = new TreeMap<>();
        orderedMap.put(3, "Dev"); // O(logN)

        System.out.println(orderedMap.get(3)); // O(logN)
        System.out.println(orderedMap.remove(3)); // O(logN)

        System.out.println(orderedMap.get(3)); // null

        // Array based
        Map<Integer, String> unorderedMap = new HashMap<>();
        unorderedMap.put(11, "President"); // O(1)

        System.out.println(unorderedMap.get(11)); // O(1)
        System.out.println(unorderedMap.remove(11)); // O(1)

        System.out.println(unorderedMap.get(11)); // null
    }

    @Test
    public void treeMap_with_multiple_insertions_on_same_key() {
        Map<Integer, String> orderedMap = new TreeMap<>();
        orderedMap.put(2, "VP");
        orderedMap.put(1, "President");
        orderedMap.put(3, "Dev1");
        orderedMap.put(3, "Dev2"); // overrides, just like HashMap

        System.out.println("Ordered by key (descending) iteration in TreeMap: ");
        for (Map.Entry<Integer, String> entry: orderedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    @Test
    public void treeMap_with_custom_comparator() {
        Map<Integer, String> orderedMap = new TreeMap<>((a,b)->b-a); // key based comparator
        orderedMap.put(2, "VP");
        orderedMap.put(1, "President");
        orderedMap.put(3, "Dev");

        System.out.println("Ordered by key (descending) iteration in TreeMap: ");
        for (Map.Entry<Integer, String> entry: orderedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    @Test
    public void treeMap_specific_utils() {
        // check the left side type changed from Map to TreeMap
        TreeMap<Integer, String> orderedMap = new TreeMap<>();
        orderedMap.put(5, "Manager");
        orderedMap.put(1, "President");
        orderedMap.put(10, "Dev");

        // All are O(logN) operations due to balanced BST
        System.out.println("firstKey: " + orderedMap.firstKey());
        System.out.println("lastKey: " + orderedMap.lastKey());

        System.out.println("firstEntry: " + orderedMap.firstEntry());
        System.out.println("lastEntry:" + orderedMap.lastEntry());

        // this would be reversed in case of decreasing order map
        System.out.println("ceilingKey(6): " + orderedMap.ceilingKey(6)); // key 10
        System.out.println("floorKey(6): " + orderedMap.floorKey(6)); // key 5

        System.out.println("ceilingEntry(6): " + orderedMap.ceilingEntry(6)); // key 10
        System.out.println("floorEntry(6): " + orderedMap.floorEntry(6)); // key 5
    }

    @Test (expected = NullPointerException.class)
    public void treeMap_null_key_throws_NPE_unless_handled_explicitly() {
        Map<Integer, String> orderedMap = new TreeMap<>();
        orderedMap.put(null, "Dev2"); // NPE
    }

}
