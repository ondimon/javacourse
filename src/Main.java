public class Main {

    public static void main(String[] args) {
        doLesson6();
    }

    public static void doLesson6() {
        testDog();

        printDelimiter();

        testCat();

        printDelimiter();

        Animal dogSharik = new Dog("Sharik");

        System.out.printf("Count animal: %d\n\r", Animal.getCount());
        System.out.printf("Count dog: %d\n\r", Dog.getCount());
        System.out.printf("Count cat: %d\n\r", Cat.getCount());
    }

    public static void testCat() {
        Animal cat = new Cat("Tom");
        cat.printAnimalInfo();

        printDelimiter();

        cat.run(200.0F);
        cat.swim(5.0F);
        cat.jump(1.5F);

        printDelimiter();

        cat.run(500.0F);
        cat.swim(15.0F);
        cat.jump(10.0F);
    }

    public static void testDog() {
        Animal dog = new Dog("Pluto");
        dog.printAnimalInfo();

        printDelimiter();

        dog.run(200.0F);
        dog.swim(5.0F);
        dog.jump(0.1F);

        printDelimiter();

        dog.run(600.0F);
        dog.swim(15.0F);
        dog.jump(10.0F);

    }

    public static void printDelimiter() {
        System.out.println("===========================================================");
        System.out.println();
    }
}
