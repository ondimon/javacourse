import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        final String guessNumber = "Numbers";
        final String guessWord = "Words";
        Scanner scanner = new Scanner(System.in);
        System.out.printf("Выберите игру. Доступны игры %s, %s:\n\r", guessNumber, guessWord);
        String game = scanner.next();
        switch (game) {
            case guessNumber: {
                playGameGuessNumber(9, 3);
                break;
            }
            case guessWord: {
                String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
                        "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive",
                        "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
                playGameGuessWord(words);
                break;
            }
            default: {
                System.out.println("Не верно указана игра");
            }
        }
        scanner.close();

    }

    public static void playGameGuessNumber(int maxNumber, int numberOfAttempts) {
        boolean playGame = true;
        Scanner scanner = new Scanner(System.in);
        while (playGame) {
            System.out.printf("Угадайте число от 0 до %d\n\rУ Вас %d попытки\n\r\n\r", maxNumber, numberOfAttempts);
            int number = generatePreciseRandIntWithRange(0, maxNumber);
            boolean playerWin = false;
            for (int i = 0; i < numberOfAttempts; i ++) {
                System.out.printf("Осталось попыток %d, введите число: \n\r", numberOfAttempts - i);
                int inputNumber = scanner.nextInt();
                if (inputNumber == number) {
                    System.out.println("Вы выиграли!");
                    playerWin = true;
                    break;
                }else if (inputNumber < number) {
                    System.out.println("Загаданное число больше!");
                }else{
                    System.out.println("Загаданное число меньше!");
                }
            }
            if (!playerWin) {
                System.out.printf("Вы проиграли. Загаданное число - %d\n\r", number);
            }

            System.out.println("Повторить игру еще раз? 1 – да / 0 – нет");
            if(scanner.nextInt() == 0) {
                playGame = false;
            }
        }
        scanner.close();
    }

    public static void playGameGuessWord(String[] words) {
        final String wordToExit = "Exit";
        Scanner scanner = new Scanner(System.in);
        int index = generatePreciseRandIntWithRange(0, words.length - 1);
        String word = words[index];
        System.out.println("Угадай загаданное слово!");
        System.out.printf("Если сдаешься, введи: %s\n\r", wordToExit);
        while (true) {
            System.out.println("Введите слово:");
            String inputWord = scanner.next();
            if(inputWord.equals(word)) {
                System.out.println("Вы угадали слово");
                break;
            }else if(inputWord.equals(wordToExit)) {
                System.out.printf("Загаданное слово было: %s\n\r", word);
                break;
            }
            String  prompt = "##############################";
            int length = Math.min(word.length(), inputWord.length());
            for(int i = 0; i < length; i ++){
                char letter = word.charAt(i);
                if(letter == inputWord.charAt(i)) {
                    prompt = setCharAt(prompt, i, letter);
                }
            }
            System.out.println("Не угадал");
            System.out.printf("Подсказка %s\n\r", prompt);

        }
        scanner.close();
    }

    public static String setCharAt(String word, int index, char ch) {
        char[] charArray = word.toCharArray();
        charArray[index] = ch;
        return new String(charArray);
    }

    static int generatePreciseRandIntWithRange(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}
