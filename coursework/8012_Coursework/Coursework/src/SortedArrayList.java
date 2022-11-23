import java.util.ArrayList;

public class SortedArrayList <E extends Comparable<E>> extends ArrayList<E> {

    public void insert(E e) {
        this.add(e);
        int endIndex = 0;

        for (endIndex = this.size() - 1; endIndex > 0 && this.get(endIndex - 1).compareTo(e) > 0; endIndex--) {
            this.set(endIndex, this.get(endIndex - 1));
        }
        this.set(endIndex, e);
    }
}





