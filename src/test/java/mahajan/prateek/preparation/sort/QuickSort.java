package mahajan.prateek.preparation.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by: pramahajan on 8/5/20 3:04 AM GMT+05:30
 */
public class QuickSort {

    @Test
    public void test1() {
        int[] a = {6,6,6,4,2,1,5};
        System.out.println(Arrays.toString(a));
        quicksort(a, 0, a.length-1);
        System.out.println(Arrays.toString(a));

    }

    void quicksort(int[] a, int l, int r) {
        if (l<r) {
            int pivot = partition(a, l, r);
            quicksort(a, l, pivot-1);
            quicksort(a, pivot+1, r);
        }
    }

    // quick select partition algo
    int partition(int[] a, int l, int r) {
        int pivot = a[r];
        int j=l;
        for (int i=l;i<r;i++) {
            if (a[i]<pivot) {
                swap(a, j, i);
                j++;
            }
        }

        swap(a, j, r);
        return j;
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
