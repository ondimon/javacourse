import java.util.Random;

public class Main {

    public static void main(String[] args) {
	    doLesson7();
    }

    public static void doLesson7() {
        Random random = new Random();

        Plate plate = new Plate(random.nextInt(100));

        int countCats = random.nextInt(10);
        Cat[] cats = new Cat[countCats];
        for ( int i = 0; i < countCats; i++ ) {
            Cat cat = new Cat(String.format("Henry %d", i), random.nextInt(20));
            cats[i]  = cat;
            cat.printInfo();
        }

        plate.info();
        for ( int i = 0; i < countCats; i++ ) {
            Cat cat = cats[i];
            if(plate.checkFood(cat.getAppetite())) {
                cat.eat(plate);
            }
            System.out.printf("is cat '%s' hungry: %b\n\r", cat.getName(), cat.isHungry());
            plate.info();
        }
    }
}
