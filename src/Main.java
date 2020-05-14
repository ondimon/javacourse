import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        doTask1();
        printDelimiter();

        doTask2();
        printDelimiter();

        doTask3();
        printDelimiter();

        doTask4();
        printDelimiter();

        doTask5();
        printDelimiter();

        doTask6();
        printDelimiter();

        doTask7();
    }

//    Задать целочисленный массив, состоящий из элементов 0 и 1.
//    Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ]. С помощью цикла и условия заменить 0 на 1, 1 на 0;
    public static void doTask1() {
        System.out.println("Task 1");
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};
        printArray(array);
        change0to1(array);
        printArray(array);
    }

    public static void change0to1(int[] array) {
        for (int i = 0; i < array.length; i ++) {
            if (array[i] == 0) {
                array[i] = 1;
            }else{
                array[i] = 0;
            }
        }
    }

//    Задать пустой целочисленный массив размером 8.
//    С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;
    public static void doTask2() {
        System.out.println("Task 2");
        int [] array = new int[8];
        int multiplier = 3;
        for (int i = 0; i < array.length; i ++) {
            array[i] =  i * multiplier;
        }
        printArray(array);
    }

//    Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;
    public static void doTask3() {
        System.out.println("Task 3");
        int [] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        printArray(array);
        for (int i = 0; i < array.length; i ++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }
        printArray(array);
    }

//    Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
//    и с помощью цикла(-ов) заполнить его диагональные элементы единицами;
    public static void doTask4() {
        System.out.println("Task 4");
        int lengthArray = 6;
        int[][] array = new int[lengthArray][lengthArray];

        for (int i = 0; i < lengthArray; i ++) {
            //Arrays.fill(array[i], 0);
            array[i][i] = 1;
            printArray(array[i]);
        }
    }

//    Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);
    public static void doTask5() {
        System.out.println("Task 5");
        int [] array = new int[6];

        for (int i = 0; i < array.length; i ++) {
            array[i] = (int) (Math.random() * 100);
        }
        printArray(array);

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for ( int val : array ) {
            if ( val < min ) {
                min = val;
            }
            if ( val > max ) {
                max = val;
            }
        }

//        Arrays.sort(array);
//        int min = array[0];
//        int max = array[array.length - 1];

        System.out.printf("min: %d \n\r", min);
        System.out.printf("max: %d \n\r", max);
    }

//    Написать метод, в который передается не пустой одномерный целочисленный массив, метод должен вернуть true,
//    если в массиве есть место, в котором сумма левой и правой части массива равны.
//
//    Примеры:
//    checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true,
//    checkBalance([1, 1, 1, || 2, 1]) → true,
//    граница показана символами ||, эти символы в массив не входят.
    public static void doTask6() {
        System.out.println("Task 6");

        doTestTask6(new int[]{2, 2, 2, 1, 2, 2, 10, 1});

        doTestTask6(new int[]{1, 1, 1, 2, 1});

        doTestTask6(new int[]{1, 1});

        doTestTask6(new int[]{1, 2});

        doTestTask6(new int[]{1, 1, 3});
    }

    public static void doTestTask6(int [] array) {
        printArray(array);
        System.out.printf("is balance: %b \n\r", checkBalance(array));
    }

    public static boolean checkBalance(int [] array) {
        for (int i = 0; i < array.length; i ++) {
            int leftSum = getSumArray(array, 0, i +1);
            int rightSum = getSumArray(array, i+1, array.length);
            if (leftSum == rightSum) {
                return true;
            }
        }
        return false;
    }

//    Написать метод, которому на вход подается одномерный массив и число n (может быть положительным, или отрицательным),
//    при этом метод должен сместить все элементы массива на n позиций.
//    Для усложнения задачи нельзя пользоваться вспомогательными массивами.

    public static void doTask7() {
        System.out.println("Task 7");
        doTestTask7(new int[]{1, 2, 3, 4, 5}, 2);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        doTestTask7(new int[]{1, 2, 3, 4, 5}, -2);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        doTestTask7(new int[]{1, 2, 3, 4, 5}, 0);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        doTestTask7(new int[]{1, 2, 3, 4, 5}, 5);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        doTestTask7(new int[]{1, 2, 3, 4, 5}, 8);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        doTestTask7(new int[]{1, 2, 3, 4, 5}, -8);
    }
    public static void doTestTask7(int[] array, int shift) {
        printArray(array);
        System.out.printf("shift: %d\n\r", shift);
        shiftElements(array, shift);
        printArray(array);

    }
    public static void shiftElements(int[] array, int shift) {
        shift = shift % array.length;

        if(shift == 0 ) return;

        if (shift > 0) {
            for (int i = 0; i < shift; i ++) {
                int buffer = array[array.length - 1];
                System.arraycopy(array, 0, array, 1, array.length - 1);
                array[0] = buffer;
            }
        }else{
            for (int i = shift; i < 0; i ++) {
                int buffer = array[0];
                System.arraycopy(array, 1, array, 0, array.length - 1);
                array[array.length - 1] = buffer;
            }
        }
    }

    public static int getSumArray(int[] array, int fromIndex, int toIndex) {
        int sum = 0;
        for (int i = fromIndex; i < toIndex; i ++) {
            sum += array[i];
        }
        return sum;
    }

    public static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void printDelimiter() {
        System.out.println("===========================================================");
        System.out.println();
    }
}
