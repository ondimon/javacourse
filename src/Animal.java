public abstract class Animal  {
    protected String name;
    protected float maxLengthForRun;
    protected float maxLengthForSwim;
    protected float maxHeightForJump;
    private static int count;

    public static int getCount() {
        return count;
    }

    public Animal(String name, float maxLengthForRun, float maxLengthForSwim, float maxHeightForJump) {
        this.name = name;
        this.maxLengthForRun = maxLengthForRun;
        this.maxLengthForSwim = maxLengthForSwim;
        this.maxHeightForJump = maxHeightForJump;
        count ++;
    }

    public void run(float length) {
        printAction("run", length, maxLengthForRun);
    }

    public void swim(float length) {
        printAction("swim", length, maxLengthForSwim);

    }
    public void jump(float height) {
        printAction("jump", height, maxHeightForJump);
    }

    public void printAnimalInfo() {
        System.out.printf("My name is %s\n\r", name);
        System.out.printf("I can run max %.2fm\n\r", maxLengthForRun);
        System.out.printf("I can swim max %.2fm\n\r", maxLengthForSwim);
        System.out.printf("I can jump max %.2fm\n\r", maxHeightForJump);

    }
    private void printAction(String action, float length, float maxLength) {
        String template;
        if(length <= maxLength) {
            template = "%s %s %.2fm";
        }else{
            template = "%s can't %s %.2fm";
        }
        System.out.printf(template, name, action, length);
        System.out.println();
    }
}
