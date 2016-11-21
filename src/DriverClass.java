import java.util.Comparator;

/**
 * Created by ren.stange on 11/16/16.
 */
public class DriverClass {
    public static void main(String[] args){
        int initialSize = Integer.parseInt(args[0]);
        Comparator<Integer> comp = new IntegerComparator();
        RMCHeapPriorityQueue<Integer> myQueue = new RMCHeapPriorityQueue<>(comp, initialSize);

        //Adds sent amount of elements to the heap queue.
        for (int i=0; i<initialSize; i++) {
            myQueue.add((int)(Math.random() * 1000));
        }

        //Print unsorted if <= 20 elements.
        if (initialSize <= 20) {
            System.out.println(myQueue.toString());
        }

        //Uses poll to add to a new array in descending sorted order.
        //Prints new sorted array before program ends, if size is <= 20.
        myQueue.sortAsc();

    }
}
