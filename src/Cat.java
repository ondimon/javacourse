public class Cat {
    private String name;
    private boolean satiety;
    private int appetite;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    public String getName() {
        return name;
    }

    public boolean isHungry() {
        return !satiety;
    }

    public int getAppetite() {
        return appetite;
    }

    public void eat(Plate p) {
        if (!satiety && p.decreaseFood(appetite) ) {
            satiety = true;
        }
    }

    public void printInfo() {
        System.out.printf("My name is %s\n\r", name);
        System.out.printf("My appetite is %d\n\r", appetite);
        System.out.printf("I'm %s\n\r", satiety ? "not hungry" : "hungry");
    }
}