
public class CalculateArray implements Runnable {
    private float[] array;
    private int start;

    public CalculateArray(float[] array, int start) {
        this.array = array;
        this.start = start;
    }

    @Override
    public void run() {
        calculateArray();
    }

    private synchronized void calculateArray() {
        Main.calculateArray(array, start);
    }
}
