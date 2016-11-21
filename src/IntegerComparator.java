import java.util.Comparator;

/**
 * Created by ren.stange on 11/16/16.
 */
public class IntegerComparator implements Comparator<Integer> {
    public int compare (Integer a, Integer b){
        return a - b;
    }
}
