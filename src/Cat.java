public class Cat extends Animal {
    private final static float MAX_LENGTH_RUN = 200.0F;
    private final static float MAX_LENGTH_SWIM = 0.0F;
    private final static float MAX_LENGTH_JUMP = 2F;
    private static int count;

    public Cat (String name) {
        super(name, MAX_LENGTH_RUN, MAX_LENGTH_SWIM, MAX_LENGTH_JUMP);
        count ++;
    }

    @Override
    public void printAnimalInfo() {
        System.out.println("Hello I'm cat");
        super.printAnimalInfo();
    }

    public static int getCount() {
        return count;
    }
}
