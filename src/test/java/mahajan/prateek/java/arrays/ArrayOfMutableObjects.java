package mahajan.prateek.java.arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by: pramahajan on 12/13/19 3:10 PM GMT+05:30
 */
public class ArrayOfMutableObjects {

    @Test
    public void array_and_map_of_mutable_objects() {
        String key = "key1";

        MutableClass mutableClass = new MutableClass(2);

        HashMap<String, MutableClass> map = new HashMap<>();
        List<MutableClass> arrayList = new ArrayList<>();

        map.put(key, mutableClass);
        arrayList.add(mutableClass);

        System.out.println(map.get(key).getCount());
        System.out.println(arrayList.get(0).getCount());

        mutableClass.setCount(5); // updating the mutable object

        System.out.println(map.get(key).getCount());  // changes reflected in both map and array
        System.out.println(arrayList.get(0).getCount());


    }

    private static class MutableClass {
        int count;

        public MutableClass(int count) {
            this.count = count;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
