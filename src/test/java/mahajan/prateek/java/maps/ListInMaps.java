package mahajan.prateek.java.maps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by: pramahajan on 6/22/20 1:35 AM GMT+05:30
 */
// Mutable lists are added by reference in maps, and not deep copied
// Hence any changes in list outside of map impacts the entry in map as well
public class ListInMaps {
    @Test
    public void list_modification_after_put_in_map() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        map.put(100, list);

        System.out.println(map);

        list.remove(0);
        System.out.println(map); // changed
    }

    @Test
    public void list_modification_after_get_from_map() {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        map.put(100, list);

        System.out.println(map);

        List<Integer> list2 = map.get(100);
        list2.remove(0);
        System.out.println(map); // changed

        System.out.println(list2);

        // same as list2 i.e they are 2 references to same location in memory
        System.out.println(list);
    }
}
