/**
 * Created by ren.stange on 11/17/16.
 */
public interface RMCPriorityQueue<E> {
    public E peek();
    public E poll();
    public void add(E elt);
    public int size();
}
