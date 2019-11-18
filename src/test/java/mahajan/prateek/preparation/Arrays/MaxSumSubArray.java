package mahajan.prateek.preparation.Arrays;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by: pramahajan on 11/9/19 9:42 PM GMT+05:30
 */
public class MaxSumSubArray {

    @Test
    public void testing() {
        int input [] = {-846930886, -1714636915, 424238335, -1649760492};
        ArrayList<Integer> b = new ArrayList<>();
        for (int anInput : input) {
            b.add(anInput);
        }
        System.out.println(maxset((b)));
    }


    public ArrayList<Integer> maxset(ArrayList<Integer> A) {

        long sumHere, sumMax = -1;
        int index = 0, first, last, firstMax = 0, lastMax = 0;

        while (index < A.size()) {
            sumHere = 0;
            while (index < A.size() && A.get(index) < 0) {
                index++;
            }

            first = index;

            while (index < A.size() && A.get(index) >= 0) {
                sumHere += A.get(index);
                index++;
            }

            last = index;

            if (sumHere > sumMax) {
                sumMax = sumHere;
                firstMax = first;
                lastMax = last;
            }
        }


        if (sumMax == -1) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(A.subList(firstMax, lastMax));
        }

    }
}
