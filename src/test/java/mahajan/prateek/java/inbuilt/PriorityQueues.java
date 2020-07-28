package mahajan.prateek.java.inbuilt;

import org.junit.Test;

import java.util.PriorityQueue;

/**
 * Created by: pramahajan on 7/29/20 3:41 AM GMT+05:30
 */

// add and poll the min/max item in logN
// remove(key) is not available
// remove(Object) or remove(index) is O(N)
// multiple objects additions are considered different entries
public class PriorityQueues {

    @Test
    public void minHeap_for_custom_objects() {
        // compile error w/o comparator for classes that don't implement Comparable interface
        //PriorityQueue<Container> minHeap = new PriorityQueue<>();

        // same order of a and b before and after -> in lambda => min heap
        PriorityQueue<Container> minHeap = new PriorityQueue<>((a,b)-> a.rank-b.rank);

        minHeap.add(new Container(1, "President"));
        minHeap.add(new Container(2, "VP"));
        minHeap.add(new Container(3, "Dev"));

        System.out.println(minHeap.poll());
        System.out.println(minHeap.poll());
        System.out.println(minHeap.poll());
    }

    @Test
    public void maxHeap_for_custom_objects() {
        // exchanged order of a and b before and after -> in lambda => max heap
        PriorityQueue<Container> maxHeap = new PriorityQueue<>((a,b)-> b.rank-a.rank);

        maxHeap.add(new Container(1, "President"));
        maxHeap.add(new Container(2, "VP"));
        maxHeap.add(new Container(3, "Dev"));

        System.out.println(maxHeap.poll());
        System.out.println(maxHeap.poll());
        System.out.println(maxHeap.poll());
    }

    @Test
    public void minHeap_for_inbuilt_wrappers() {
        // no comparator needed since Integer implements Comparable interface
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.add(1);
        minHeap.add(10);
        minHeap.add(20);

        System.out.println(minHeap.poll());
        System.out.println(minHeap.poll());
        System.out.println(minHeap.poll());
    }

    @Test
    public void heap_remove_object() {
        PriorityQueue<Container> maxHeap = new PriorityQueue<>((a,b)-> b.rank-a.rank);
        Container dev = new Container(3, "Dev");
        Container president = new Container(1, "President");
        Container vp = new Container(2, "VP");

        maxHeap.add(president);
        maxHeap.add(vp);
        maxHeap.add(dev);

        maxHeap.remove(vp); // O(n) operation

        System.out.println(maxHeap.size()); // 2
        System.out.println(maxHeap.poll());
        System.out.println(maxHeap.poll());

        System.out.println(maxHeap.size()); // 0
        System.out.println(maxHeap.poll()); // would return null, no error
    }

    @Test
    public void poll_empty_heap_returns_null() {
        PriorityQueue<Container> maxHeap = new PriorityQueue<>((a,b)-> b.rank-a.rank);
        System.out.println(maxHeap.size()); // 0
        System.out.println(maxHeap.poll()); // would return null, no error
    }

    @Test
    public void heap_add_same_object_multiple_times() {
        PriorityQueue<Container> maxHeap = new PriorityQueue<>((a,b)-> b.rank-a.rank);
        Container dev = new Container(3, "Dev");
        Container president = new Container(1, "President");
        Container vp = new Container(2, "VP");

        maxHeap.add(president);
        maxHeap.add(vp);
        maxHeap.add(dev);
        maxHeap.add(dev); // multiple additions of same object are treated as different entries

        System.out.println(maxHeap.size()); // 4
        System.out.println(maxHeap.poll());
        System.out.println(maxHeap.poll());
        System.out.println(maxHeap.poll());
    }

    private static class Container {
        int rank;
        String name;

        public Container(int rank, String name) {
            this.rank = rank;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Container{" + "rank=" + rank + ", name='" + name + '\'' + '}';
        }
    }

}
