package Arrays;

import java.util.*;

public class Utils {

    public static int [] getArrayAfterFour(int [] array) throws RuntimeException {
        if(array.length == 0) {
            throw new RuntimeException("Array is empty");
        }
        boolean isCorrect = false;
        int start = 0;

        for ( int i = array.length  - 1; i >= 0 ; i-- ) {
            int el = array[i];

            if(el == 4) {
                start = i + 1;
                isCorrect = true;
                break;
            }
        }
        if(!isCorrect) {
            throw new RuntimeException("Array does not contain 4");
        }
        return Arrays.copyOfRange(array, start, array.length );
    }

    public static boolean arrayCorrect(int[] array) {
        Set<Integer> uniqueNumbers= new HashSet<>();
        int[] requiredNumbers = new int[]{1,4};

        for ( int el : array ) {
            uniqueNumbers.add(el);
        }

        if(uniqueNumbers.size() != requiredNumbers.length) {
            return false;
        }

        for(int number : requiredNumbers) {
            if(!uniqueNumbers.contains(number)) {
                return false;
            }
        }
        return true;
    }
}
