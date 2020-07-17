import java.util.ArrayList;
import java.util.Collections;

public class ArrayUtilities<T> {
    T[] array;

    public ArrayUtilities(T[] array) {
        this.array = array;
    }

    public T[] changeElements(int el1, int el2) {
       T[] copyArray = array.clone();
       copyArray[el1] = array[el2];
       copyArray[el2] = array[el1];
       return copyArray;
    }

    public ArrayList<T> getArrayList() {
        ArrayList<T> list =  new ArrayList<>();
        Collections.addAll(list, array);
        return list;
    }
}
