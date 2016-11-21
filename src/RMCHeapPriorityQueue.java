import java.util.Comparator;

/**
 * Created by ren.stange on 11/16/16.
 */
public class RMCHeapPriorityQueue<E> implements RMCPriorityQueue<E>{

    private E[] data;
    private int size;
    private Comparator<E> comparator;

    public RMCHeapPriorityQueue(Comparator<E> comparator){
        new RMCHeapPriorityQueue(comparator, 100);
    }

    public RMCHeapPriorityQueue(Comparator<E> comparator, int initialSize){
        this.comparator = comparator;
        this.data = (E[]) new Object[initialSize];
        this.size = 0;
    }

    public E peek(){
        if (this.data == null || this.data.length == 0) {
            return null;
        }
        return data[0];
    }

    public E poll() {
        if (this.data == null || this.size == 0) {
            System.out.println("Queue is empty!");
            return null;
        }
        E returnValue = this.data[0];
        //Need to remove and percolate up.
        //Setting last element equal to first element, then Percolating down.
        this.data[0] = this.data[this.size - 1];
        percolateDown(0);
        this.size--;
        this.data[this.size] = null;
        return returnValue;
    }

    public void add(E elt) {
        if (this.data.length <= this.size) {
            this.growArray();
        }
        this.data[size++] = elt;
        percolateUp(size-1);
    }

    public int size(){
        return this.size;
    }

    private int leftChildOf(int index){
        return (index * 2 + 1);
    }

    private int rightChildOf(int index){
        return (index * 2 + 2);
    }

    private int parentOf(int index){
        if (index > 0) {
            return ((index - 1) / 2);
        }
        return 0;
    }

    private void swap(int a, int b){
        E tempValue = this.data[a];
        this.data[a] = this.data[b];
        this.data[b] = tempValue;
    }

    private void percolateUp(int index) {
        if ((index > 0) && (comparator.compare(data[parentOf(index)], data[index]) < 0)) {
            swap(parentOf(index), index);
            percolateUp(parentOf(index));
        }
    }

    private void percolateDown(int index) {
        if ((leftChildOf(index) < this.size) && (rightChildOf(index) < this.size) && (data[leftChildOf(index)] != null) && (data[rightChildOf(index)] != null)) {
            if ((comparator.compare(data[index], data[leftChildOf(index)]) < 0) && (comparator.compare(data[leftChildOf(index)], data[rightChildOf(index)]) > 0)) {
                swap(index, leftChildOf(index));
                percolateDown(leftChildOf(index));
            } else if (comparator.compare(data[index], data[rightChildOf(index)]) < 0) {
                swap(index, rightChildOf(index));
                percolateDown(rightChildOf(index));
            }
        } else if ((leftChildOf(index) < this.size) && (data[leftChildOf(index)] != null) && (comparator.compare(data[index], data[leftChildOf(index)]) < 0)){
            swap(index, leftChildOf(index));
            percolateDown(leftChildOf(index));
        }
    }

    private void growArray(){
        //Create a temp Array with twice the amount of elements of the old.
        E[] tempArray = (E[]) new Object[this.size * 2];
        for (int i=0; i<this.size; i++) {
            tempArray[i] = this.data[i];
        }
        this.data = tempArray;
    }

    public String toString() {
        String tempString = "";
        for (int i=0; i<this.size; i++) {
            tempString += this.data[i];
            if (i!=(this.size - 1)) {
                tempString += ", ";
            }
        }
        return tempString;
    }

    public void sortAsc() {
        String printString = "[";
        int endCount = this.size;
        for (int i=0; i<endCount; i++) {
            printString += this.poll();
            if (i < endCount-1) {
                printString += ", ";
            }
        }
        printString += "]";
        if (endCount <= 20) {
            System.out.println(printString);
        }
    }

}
