package mahajan.prateek.preparation.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by: pramahajan on 3/12/20 1:51 AM GMT+05:30
 */
public class ThreeSum {

    //https://leetcode.com/problems/3sum/
    @Test
    public void threeSum() {
        int [] nums = {-1, 0, 1, 2, -1, -4};

        List<List<Integer>> arr = new ArrayList<>();
        Arrays.sort(nums);

        int n = nums.length;

        Map<Integer, Integer> map = new HashMap<>();

        for (int i=0;i<n;i++) {
            map.put(nums[i], i);
        }

        //System.out.println(map);

        int a = Integer.MAX_VALUE, b = Integer.MAX_VALUE, c = Integer.MAX_VALUE;

        for (int i=0;i<n-2;i++) {

            // avoiding duplicates with same first element
            while(i<n-2 && nums[i] == a)  {  // notice how we are checking i< n-2 in while loop
                i++;
            }

            if (i>=n-2) { // optimization - first element can't be from last 2
                break;
            }

            for (int j=i+1; j<n-1;j++) {

                // avoiding duplicates with fixed first element and multiple second elements with same value
                while(j<n-1 && nums[i] == a && nums[j] == b)  {
                    j++;
                }

                if (j>=n-1) {  // optimization - second element can't be the last one
                    break;
                }


                int x = nums[i], y = nums[j];
                int z = (x+y)*(-1);
                if (map.containsKey(z) && map.get(z) > j) {
                    arr.add(Arrays.asList(x,y,z));
                    a = x;
                    b = y;
                    c = z;
                }
            }
        }

        System.out.println(arr);

    }

}
