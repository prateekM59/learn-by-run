package mahajan.prateek.prep.recursion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by: pramahajan on 11/5/19 11:19 PM GMT+05:30
 */
public class BoxStacking {

    static int count = 0;


    @Test // THIS IS NOT WORKING
    // https://www.techiedelight.com/box-stacking-problem/
    public void find_max_height_by_stacking_infinite_boxes_with_given_criteria() {
        List<Box> boxList = new ArrayList<>();

//        boxList.add(new Box(4,2, 5)); //10
//        boxList.add(new Box(3,8,6)); // 8
//        boxList.add(new Box(8,6,3)); //20


        boxList.add(new Box(4,2, 5)); //10
        boxList.add(new Box(5,2, 4)); // 8
//        boxList.add(new Box(2,4, 5)); //20
//
//        boxList.add(new Box(3,2, 5));
//        boxList.add(new Box(1,3, 6));
        boxList.add(new Box(6,3, 1)); //3
//
//        boxList.add(new Box(3,2, 1));
        boxList.add(new Box(2,3, 1)); //3
        boxList.add(new Box(1,2, 3)); //6
//
        boxList.add(new Box(6,3, 8)); //24
        boxList.add(new Box(3,8, 6)); //48
        boxList.add(new Box(8,6, 3)); // 18


        //System.out.println(createStack(boxList));
        //System.out.println(maxHeight3(boxList).height);

        for (int i =0; i<boxList.size(); i++) {
            Output output = maxHeight3(boxList, i);
            System.out.println(output.height);
        }

    }




    private Output maxHeight3(List<Box> boxes, int index) {
        if(boxes.size() == 1) {
            return new Output(boxes.get(0), boxes.get(0).height);
        }

        Box currentBox = boxes.get(index);

        List<Box> boxesWithoutCurrent = new ArrayList<>(boxes);
        boxesWithoutCurrent.remove(currentBox);

        Output maxOutput = new Output(null, 0);

        for (int i = 0; i< boxesWithoutCurrent.size(); i++) {
            Output outputI = maxHeight3(boxesWithoutCurrent, i);

            if (outputI.height > maxOutput.height) {
                maxOutput = outputI;
            }
        }

        if (maxOutput.height +currentBox.height > currentBox.height) {
            return new Output(currentBox, maxOutput.height +currentBox.height);
        } else {
            return new Output(currentBox, currentBox.height);
        }

    }




    private static class Box {
        int height, width, length;

        private Box(int height, int width, int length) {
            this.height = height;
            this.width = width;
            this.length = length;
        }

        private boolean canBeUnder(Box box) { // if base area of upper box is less than base area of lower box
            return box.length * box.width < this.length * this.width;
        }

        private boolean canBeAbove(Box box) { // if base area of upper box is less than base area of lower box
            return box.length * box.width > this.length * this.width;
        }
    }


    private static class BoxComparator implements Comparator<Box> {
        @Override
        public int compare(Box x, Box y){
            return y.width*y.length - x.width*x.length;
        }
    }

    private static class Output {
        Box base;
        int height;

        public Output(Box base, int height) {
            this.base = base;
            this.height = height;
        }
    }
}
