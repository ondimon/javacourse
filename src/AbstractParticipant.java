public class AbstractParticipant implements Participant{
    protected String name;
    protected int maxJump;
    protected int maxRun;

    public AbstractParticipant(String name, int maxJump, int maxRun) {
        this.name = name;
        this.maxJump = maxJump;
        this.maxRun = maxRun;
    }

    @Override
    public void jump() {
        System.out.printf("%s is jumping...\n\r", name);
    }

    @Override
    public void run() {
        System.out.printf("%s is running...\n\r", name);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxRun() {
        return maxRun;
    }

    @Override
    public int getMaxJump() {
        return maxJump;
    }
}
