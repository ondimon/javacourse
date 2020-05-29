public class Dog extends Animal {
    private final static float MAX_LENGTH_RUN = 500.0F;
    private final static float MAX_LENGTH_SWIM = 10.0F;
    private final static float MAX_LENGTH_JUMP = 0.50F;
    private static int count;

    public Dog (String name) {
        super(name, MAX_LENGTH_RUN, MAX_LENGTH_SWIM, MAX_LENGTH_JUMP);
        count ++;
    }

    @Override
    public void printAnimalInfo() {
        System.out.println("Hello I'm dog");
        super.printAnimalInfo();
    }

    public static int getCount() {
        return count;
    }
}
