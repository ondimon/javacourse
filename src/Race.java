import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Race {
    private ArrayList<Stage> stages;
    private CountDownLatch readiness;
    private CyclicBarrier start;
    private Lock prize;


    private CountDownLatch finish;

    public ArrayList<Stage> getStages() {
        return stages;
    }

    public CountDownLatch getReadiness() {
        return readiness;
    }

    public CyclicBarrier getStart() {
        return start;
    }

    public CountDownLatch getFinish() {
        return finish;
    }

    public Lock getPrize() {
        return prize;
    }

    public Race(int carCount, Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.readiness = new CountDownLatch(carCount);
        this.start = new CyclicBarrier(carCount);
        this.finish = new CountDownLatch(carCount);
        this.prize = new ReentrantLock();
    }
}
