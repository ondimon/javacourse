public class Main {

    public static void main(String[] args) {
        doLesson();
    }

    public static void doLesson() {
        String[][] array = new String [][] {
                {"1", "2" , "3", "4"},
                {"1", "2" , "3", "4"},
                {"1", "2" , "3", "4"},
                {"1", "2" , "3", "4"}
        };
        testArray(array);

        String[][] array2 = new String [][] {
                {"1", "2" , "3", "4"},
                {"1", "2" , "3", "4"},
                {"1", "2" , "3", "4"}
        };
        testArray(array2);

        String[][] array3 = new String [][] {
                {"1", "2" , "3"},
                {"1", "2" , "3"},
                {"1", "2" , "3"},
                {"1", "2" , "3"}
        };
        testArray(array3);

        String[][] array4 = new String [][] {
                {"1", "2" , "3", "4"},
                {"1", "2" , ".", "4"},
                {"1", "2" , "3", "4"},
                {"1", "2" , "3", "4"}
        };
        testArray(array4);
    }

    public static void testArray(String[][] array) {
        int sum;

        try {
            sum = getSumArray(array);
            System.out.println(sum);
        } catch (MyArrayDataException e) {
            System.out.println(e.getMessage());
        } catch (MyArraySizeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static int getSumArray(String[][] array) throws MyArrayDataException, MyArraySizeException {
        int result = 0;
        if(array == null || array.length != 4 || array[0].length != 4) {
            throw new MyArraySizeException("Array must be 4x4");
        }

        for ( int i = 0; i < array.length; i++ ) {
            for ( int j = 0; j < array.length; j++ ) {
                int number;
                try {
                    number = Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    String message = String.format("Unknown character in [%d][%d]. Must be a number", i + 1, j + 1);
                    throw new MyArrayDataException(message);
                }
                result += number;
            }
        }
        return result;

    }

}
