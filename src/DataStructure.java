import java.util.LinkedList;

public class DataStructure {
    public static void main(String[] args) {
        testLists();
    }

    public static void testLists() {
        DirectionalList directionalList = new OneDirectionalList();
        testList(directionalList);

        delimiter();

        DirectionalList twoDirectionalList = new TwoDirectionalList();
        testList(twoDirectionalList);

        delimiter();

        String[] Array = new String[] {
                "1232",
                "sdfds",
                "sdf3432",
                "asdasds"
        };

        DirectionalList directionalListFromArray = new TwoDirectionalList(Array);
        printInfo(directionalListFromArray);

        delimiter();

        DirectionalList twoDirectionalListFromArray = new TwoDirectionalList(Array);
        printInfo(twoDirectionalListFromArray);

    }

    public static void testList(DirectionalList directionalList) {
        System.out.println(directionalList.getClass().getCanonicalName());

        directionalList.add("val1");
        directionalList.add("val2");
        directionalList.add("val3");
        directionalList.add("val4");

        printInfo(directionalList);

        System.out.println("Removed? - " + directionalList.remove("val6"));
        System.out.println("Removed? - " + directionalList.remove("val3"));

        printInfo(directionalList);

    }

    public static void printInfo(DirectionalList directionalList) {
        System.out.println(directionalList.toString());
        System.out.println("Size: " + directionalList.size());
    }

    public static void delimiter() {
        System.out.println("===========================================================");
        System.out.println();
    }
}
