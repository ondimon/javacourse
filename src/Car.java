public class Car implements Runnable {

    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            race.getReadiness().countDown();
            race.getStart().await();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        boolean isWinner = false;

        if (race.getPrize().tryLock()) {
            isWinner = true;
            System.out.println(this.name + " победил!!!!!");
        }

        race.getFinish().countDown();

        if(isWinner) {
            try {
                race.getFinish().await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            race.getPrize().unlock();
        }

    }
}