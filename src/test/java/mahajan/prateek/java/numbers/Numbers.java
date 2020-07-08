package mahajan.prateek.java.numbers;

import org.junit.Test;

/**
 * Created by: pramahajan on 7/9/20 1:02 AM GMT+05:30
 */
public class Numbers {

    @Test
    public void double_and_int_arithmetic_operations_result_in_double() {
        double d = 10.20d;
        int i = 3;
        System.out.println(d-i); // double

        //int c = d-i; // compile error => double can not be assigned to int

        int e = (int)d-i; // cast to int
        System.out.println(e);
    }

    @Test
    public void double_and_int_logical_operations_work_fine() {
        double d = 10.20d;
        int i = 10;
        System.out.println(d<i);

        int j = 11;
        System.out.println(d>j);

        double d2 = 10.00d;
        System.out.println(d2==i);
    }
}
