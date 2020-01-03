package mahajan.prateek.java.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by: pramahajan on 12/4/19 2:18 AM GMT+05:30
 */
public class ArraysCollectionsExamples {


    @Test
    public void arrays_as_generics_in_list() {
        List<int[]> a = new ArrayList<>();
        a.add(new int[] {1,2,3});
        a.add(new int[] {7,8});

        System.out.println("First row");

        System.out.println(a.get(0)[0]);
        System.out.println(a.get(0)[1]);
        System.out.println(a.get(0)[2]);


        System.out.println("Second row");

        System.out.println(a.get(1)[0]);
        System.out.println(a.get(1)[1]);
        //System.out.println(a.get(1)[2]); ArrayIndexOutOfBoundsException

    }

    @Test
    public void arrays_as_generics_in_comparator() {
        int[][] a = {{1,11}, {3, 12}, {2, 10}};
        Arrays.sort(a, new Comparator<int[]>() {

            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        System.out.println(a[0][0] + " "  + a[0][1]);
        System.out.println(a[1][0] + " "  + a[1][1]);
        System.out.println(a[2][0] + " "  + a[2][1]);

    }

    @Test
    public void collections_toArray_primitive_array() {
        List<int[]> a = new ArrayList<>();
        a.add(new int[] {1,2,3});
        a.add(new int[] {7,8});

        int [][] b = a.toArray(new int[a.size()][]); // row dimension is mandatory

        //Integer [][] c = a.toArray(new Integer[a.size()][]); // ArrayStoreException

        System.out.println(b[0][0] + " "  + b[0][1] + " " + b[0][2]);
        System.out.println(b[1][0] + " "  + b[1][1]);


        //System.out.println(b[1][0] + " "  + b[1][1] + " " + b[1][2]); ArrayIndexOutOfBoundsException

    }

    @Test
    public void collections_toArray_wrapper_array() {
        List<Integer[]> a = new ArrayList<>();
        a.add(new Integer[] {1,2,3});
        a.add(new Integer[] {7,8});

        Integer [][] b = a.toArray(new Integer[a.size()][]); // row dimension is mandatory

        //Integer [][] c = a.toArray(new int[a.size()][]); // Incompatible type error

        System.out.println(b[0][0] + " "  + b[0][1] + " " + b[0][2]);
        System.out.println(b[1][0] + " "  + b[1][1]);


        //System.out.println(b[1][0] + " "  + b[1][1] + " " + b[1][2]); ArrayIndexOutOfBoundsException

    }
}
