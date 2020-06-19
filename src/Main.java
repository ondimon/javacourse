import java.util.*;

public class Main {

    public static void main(String[] args) {
        doTask1();
        printDelimiter();
        doTask2();
    }

    public static void doTask1() {
        String[] wordsArray = new String[] {
            "test1",
            "test",
            "test2",
            "test3",
            "test4",
            "test1",
            "test1",
            "test",
            "test5",
            "test6",
            "test5",
            "test6",
        };

        printStatistics(wordsArray);
    }

    public static void printStatistics(String[] wordsArray) {

       Map<String, Integer> wordCounter = new HashMap<>();
       for ( String word : wordsArray ) {
           if(wordCounter.containsKey(word)) {
               wordCounter.put(word, wordCounter.get(word) + 1);
           } else {
               wordCounter.put(word, 1);
           }
       }

       System.out.println("Unique words:");
       for(String word : wordCounter.keySet()) {
            System.out.println(word);
       }

       System.out.println("word count:");
       for (Map.Entry<String, Integer> e :  wordCounter.entrySet()) {
            System.out.printf("%s: %d\n\r", e.getKey(), e.getValue());
       }


    }

    public static void doTask2() {
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.add("test", "12312321");
        phoneBook.add("test2", "56765756");
        phoneBook.add("test2", "567567675");
        System.out.println(phoneBook.get("test").toString());
        System.out.println(phoneBook.get("test2").toString());

    }
    public static void printDelimiter() {
        System.out.println("===========================================================");
        System.out.println();
    }
}
