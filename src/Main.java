import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        doTask1();
        doTask2();
    }

    static void doTask1() {
        Integer[] array = new Integer[] {1,2,3,4,5};
        ArrayUtilities<Integer> arrayUtilities = new ArrayUtilities<>(array);

        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(arrayUtilities.changeElements(1, 2)));
        System.out.println(arrayUtilities.getArrayList().toString());
    }
    static void doTask2() {
        Box<Apple> boxAppalls = new Box<>();
        Box<Apple> newBoxAppalls = new Box<>();
        Box<Orange> boxOranges = new Box<>();

        boxAppalls.add(new Apple());
        boxAppalls.add(new Apple());
        boxAppalls.add(new Apple());

        boxOranges.add(new Orange());
        boxOranges.add(new Orange());
        boxOranges.add(new Orange());

        System.out.printf("box weight is: %.1f\n\r", boxAppalls.getWeight());
        System.out.printf("box weight is: %.1f\n\r", boxOranges.getWeight());
        System.out.printf("boxes is the same: %b\n\r", boxAppalls.compare(boxOranges));

        boxAppalls.moveTo(newBoxAppalls);

        System.out.printf("box is empty: %b\n\r", boxAppalls.isEmpty());
        System.out.printf("box is empty: %b\n\r", newBoxAppalls.isEmpty());


    }

}
