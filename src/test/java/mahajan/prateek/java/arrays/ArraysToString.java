package mahajan.prateek.java.arrays;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by: pramahajan on 6/7/20 10:08 PM GMT+05:30
 */

// TL;DR
// Use Arrays.toString() for 1D primitive/wrapper array
// Use Arrays.deepToString() for multi-dimension primitive/wrapper array

// Avoid Object.toString() for any array
// Use Object.toString() for any collection - set, list, etc.
public class ArraysToString {

    @Test
    public void Object_toString_vs_Arrays_toString_for_wrappers() {
        String[] strs = new String[] {"str1", "str2"};
        System.out.println(strs.toString()); // [Ljava.lang.String;@4361bd48
        System.out.println(strs); // same as above, [Ljava.lang.String;@4361bd48

        System.out.println(Arrays.toString(strs)); // [str1, str2]
    }

    @Test
    public void Object_toString_vs_Arrays_toString_for_primitives() {
        int[] arr = {1,2,3};
        System.out.println(arr.toString()); // [I@4361bd48
        System.out.println(arr); // same as above, [I@4361bd48

        System.out.println(Arrays.toString(arr)); // [1, 2, 3]
    }

    @Test
    public void Object_toString_on_collections_works_fine() {
        Set<String> set = new HashSet<>();
        set.add("str1");
        set.add("str2");
        System.out.println(set.toString()); // [str1, str2]
        System.out.println(set); // same as above, [str1, str2]
    }

    // Use Arrays.toString() for single dimension wrapper array and Arrays.deepToString() for multi-dimension wrapper array
    @Test
    public void Arrays_deepToString_vs_Arrays_toString_on_wrappers() {
        String[] strs1 = new String[] {"str1", "str2"};
        System.out.println(Arrays.toString(strs1)); // [str1, str2] OK
        System.out.println(Arrays.deepToString(strs1)); // [str1, str2] OK

        String[][] strs2 = new String[][] {{"str1", "str2"},{"str3", "str4"}};
        System.out.println(Arrays.toString(strs2)); // [[Ljava.lang.String;@53bd815b, [Ljava.lang.String;@2401f4c3] NOT OK
        System.out.println(Arrays.deepToString(strs2)); // [[str1, str2], [str3, str4]] OK
    }

    // Use Arrays.toString() for single dimension primitive array and Arrays.deepToString() for multi-dimension primitive array
    @Test
    public void Arrays_deepToString_vs_Arrays_toString_on_primitives() {
        int[] arr1 = {1,2,3};
        System.out.println(Arrays.toString(arr1)); // [1, 2, 3]
        // System.out.println(Arrays.deepToString(arr1)); // compile ERROR

        int[][] arr2 = {{1,2,3},{4,5,6}};
        System.out.println(Arrays.toString(arr2)); // [[I@4361bd48, [I@53bd815b]
        System.out.println(Arrays.deepToString(arr2)); // [[1, 2, 3], [4, 5, 6]]
    }

}
