import java.util.Arrays;

public class Main {
    static final int SIZE = 10000000;
    static final int COUNT_THREAD = 2;
    static final int PART_SIZE = SIZE / COUNT_THREAD;

    public static void main(String[] args) {
        testThreaded();
    }

    public static void testThreaded() {

        float[] arraySingle = new float[SIZE];
        float[] arrayMultiple = new float[SIZE];
        initializeArray(arraySingle);
        initializeArray(arrayMultiple);

        doSingleThreaded(arraySingle);
        doMultipleThreaded(arrayMultiple);

        System.out.printf("Arrays is equals: %b", Arrays.equals(arraySingle, arrayMultiple));
    }

    public static void doSingleThreaded(float[] array) {
        long start = System.currentTimeMillis();
        calculateArray(array, 0);
        long end =  System.currentTimeMillis();
        System.out.printf("Single threaded: %d \n\r", end - start);
    }

    public static void doMultipleThreaded(float[] array) {
        long start = System.currentTimeMillis();

        float[][] partArray = new float[COUNT_THREAD][PART_SIZE];
        Thread[] treads = new Thread[COUNT_THREAD];

        for ( int i = 0; i < COUNT_THREAD; i++ ) {
            System.arraycopy(array, i * PART_SIZE, partArray[i], 0, PART_SIZE);
            treads[i] = new Thread(new CalculateArray(partArray[i], i * PART_SIZE));
        }

        for ( int i = 0; i < COUNT_THREAD; i++ ) {
            treads[i].start();
        }

        for ( int i = 0; i < COUNT_THREAD; i++ ) {
            try {
                treads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for ( int i = 0; i < COUNT_THREAD; i++ ) {
            System.arraycopy(partArray[i], 0, array, PART_SIZE * i, PART_SIZE);
        }

        long end =  System.currentTimeMillis();
        System.out.printf("Multiple threaded: %d\n\r", end - start);
    }

    public static void initializeArray(float[] array) {
        Arrays.fill(array, 1.0f);
    }

    public static void calculateArray(float[] array, int start) {
        for ( int i = 0; i < array.length; i++ ) {
            array[i] = (float)(array[i] * Math.sin(0.2f + start / 5) * Math.cos(0.2f + start / 5) * Math.cos(0.4f + start / 2));
            start ++;
        }    
    }
}

