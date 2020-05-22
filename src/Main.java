import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    /**
     * Tic Tac Toe
     * <p>
     * 1. Инициализровать игровое поле
     * 2. Игрок - Х, Компьютер - О
     * 3. Игрок всегда ходит первый
     * 4. Реализовать ход игрока по координатам [X, Y]
     * 5. Реализовать ход компьютера по координатам [X, Y]
     * 6. Защита от неверных координат [X, Y] введенных игроком
     * 7. Защита от попытки ввести координаты [X, Y] в уже занятую ячейку
     * 8. Игра до победы
     * 9. Проверка победы
     * 10. Победа
     * 11. Проверка ничьи
     */
    public static void main(String[] args) {
        startGameTicTacToe();
    }

    static void startGameTicTacToe() {
        playTicTacToe(getLengthFieldFromPlayer());
    }

    static int getLengthFieldFromPlayer() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter field size:");
        return scanner.nextInt();
    }

    static void playTicTacToe(int length) {
        char[][] field = getField(length);
        char playerSign = DOT_X;
        char computerSign = DOT_O;
        char currentPlayerSign = playerSign;
        String PlayerName = getPlayerName();
        String currentPlayerName = PlayerName;

        drawField(field);
        System.out.println("lets play!");

        boolean isWin;
        boolean isDraw;
        do {
            move(field, currentPlayerSign);
            isWin = checkWin(field, currentPlayerSign);
            isDraw = isFieldFull(field);
            if (!isWin && !isDraw) {
                currentPlayerSign = currentPlayerSign == playerSign ? computerSign : playerSign;
                currentPlayerName = currentPlayerSign == playerSign ? PlayerName : "Computer";
                drawField(field);
            }
        } while(!(isWin || isDraw));

        if (isWin) {
            System.out.println(String.format("Congrats!!! You are winner Mr. %s", currentPlayerName));
        }else{
            System.out.println("Draw");
        }

        drawField(field);
    }

    static String getPlayerName() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name:");
        return scanner.next();
    }

    static void move(char[][] field, char currentPlayerSign) {
        if (currentPlayerSign == DOT_X) {
            movePlayerSign(field, currentPlayerSign);
        } else {
            moveComputer(field, currentPlayerSign);
        }
    }

    static boolean checkWin(char[][] field, char currentPlayerSign) {
        return checkHorizontalWin(field, currentPlayerSign)
                || checkVerticalWin(field, currentPlayerSign)
                || checkDiagonalWin(field, currentPlayerSign);
    }

    static boolean isFieldFull(char[][] field) {
        for(int i = 0; i < field.length; i ++) {
            for (int j = 0; j < field.length; j ++) {
                if (isCellEmpty(field, i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    static boolean checkHorizontalWin(char[][] field, char currentPlayerSing) {
        for(int i = 0; i < field.length; i ++) {
            int countSign = 0;
            for (int j = 0; j < field.length; j ++) {
                if(field[i][j] == currentPlayerSing) {
                    countSign ++;
                }else{
                    countSign = 0;
                }
                if (countSign == getLengthLineToWin(field.length)) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean checkVerticalWin(char[][] field, char currentPlayerSing) {
        for(int i = 0; i < field.length; i ++) {
            int countSign = 0;
            for (int j = 0; j < field.length; j ++) {
              if(field[j][i] == currentPlayerSing) {
                  countSign ++;
              }else{
                  countSign = 0;
              }
              if (countSign == getLengthLineToWin(field.length)) {
                  return true;
              }
            }
        }
       return false;
    }

    static boolean checkDiagonalWin(char[][] field, char currentPlayerSing) {
        int countSign = 0;
        for(int i = 0; i < field.length; i ++) {
            if(field[i][i] == currentPlayerSing) {
                countSign ++;
            }else{
                countSign = 0;
            }
            if (countSign == getLengthLineToWin(field.length)) {
                return true;
            }
        }


        countSign = 0;
        for(int i = 0; i < field.length; i ++) {
            if(field[i][field.length - 1 - i] == currentPlayerSing) {
                countSign ++;
            }else{
                countSign = 0;

            }
            if (countSign == getLengthLineToWin(field.length)) {
                return true;
            }
        }

        return false;
    }

    static int getLengthLineToWin(int fieldSize) {
        if (fieldSize > 3) {
            return 4;
        }else{
            return fieldSize;
        }
    }

    static void moveComputer(char[][] field, char computerSign) {
        int x = 0;
        int y = 0;
//        Random random = new Random();
//
//        do {
//            x = random.nextInt(field.length);
//            y = random.nextInt(field.length);
//        } while (!isCellEmpty(field, x, y));
        int[][] fieldWeigh = getFieldWeigh(field, computerSign);
        int maxWeigh = 0;
        for ( int i = 0; i < field.length; i++ ) {
            for ( int j = 0; j < field.length; j++ ) {
                if(maxWeigh < fieldWeigh[i][j]) {
                    maxWeigh = fieldWeigh[i][j];
                    x = i;
                    y = j;
                }
            }
        }
        System.out.println("Computer's move ...");
        System.out.println(String.format("Computer decided choose coordinates [%s, %s]", x + 1, y + 1));
        field[x][y] = computerSign;
    }

    static int[][] getFieldWeigh(char[][] field, char computerSign) {
        int[][] fieldWeigh = new int[field.length][field.length];

        for(int i = 0; i < field.length; i ++) {
            for (int j = 0; j < field.length; j ++) {
                if(!isCellEmpty(field, i, j)) {
                    addWeigh(fieldWeigh, i, j, -1);
                }
            }
        }

        for(int i = 0; i < field.length; i ++) {
            for (int j = 0; j < field.length; j ++) {
                if(!isCellEmpty(field, i, j) && field[i][j] != computerSign) {
                    fillWeighAroundPoint(field, fieldWeigh, i, j, computerSign);
                }
            }
        }

        return  fieldWeigh;
    }

    static void fillWeighAroundPoint(char[][] field, int[][] fieldWeigh, int x, int y, char computerSign) {
        int weigh;
        char playerSign = computerSign == DOT_O ? DOT_X : DOT_O;

        for ( int k = -1; k < 2; k++ ) {
            for ( int l = -1; l < 2; l++ ) {
                int i = x + k;
                int j = y + l;
                if(!checkCoordinates(field.length, i, j) || fieldWeigh[i][j] != 0) continue;

                char[][] copyField = getCopyField(field);

                if (predictWin(copyField, i, j, computerSign)) {
                    weigh = 3;
                }else if(predictWin(copyField, i, j, playerSign)) {
                    weigh = 2;
                }else{
                    weigh = 1;
                }
                addWeigh(fieldWeigh, i, j, weigh);
                if (weigh == 3) {
                    return;
                }
            }
        }
    }

    static boolean predictWin(char[][] field, int x, int y, char currentPlayerSign) {
        field[x][y] = currentPlayerSign;
        return checkWin(field, currentPlayerSign);
    }

    static char[][] getCopyField(char[][] field) {
        char[][] copyField = new char[field.length][field.length];
        for ( int i = 0; i < field.length; i++ ) {
            System.arraycopy(field[i], 0, copyField[i], 0, field.length);
        }
        return  copyField;
    }

    static void addWeigh(int[][] fieldWeigh, int x, int y, int weigh) {
        if(!checkCoordinates(fieldWeigh.length, x, y) || fieldWeigh[x][y] != 0) return;
        fieldWeigh[x][y] = weigh;
    }

    static void movePlayerSign(char[][] field, char playerSign) {
        int x;
        int y;
        boolean isEmptyCell;
        String templateCoordinates = geTemplateCoordinates(field.length);

        do {
            boolean isCorrectCoordinates;
            do {
                Scanner scanner = new Scanner(System.in);
                System.out.println(String.format("Input X-coordinates %s", templateCoordinates));
                x = scanner.nextInt() - 1;
                System.out.println(String.format("Input Y-coordinates %s", templateCoordinates));
                y = scanner.nextInt() - 1;

                isCorrectCoordinates = checkCoordinates(field.length, x, y);
                notifyIncorrectCoordinates(isCorrectCoordinates);
            } while (!isCorrectCoordinates);

            isEmptyCell = isCellEmpty(field, x, y);
            notifyOccupiedCell(isEmptyCell, x, y);
        } while (!isEmptyCell);

        field[x][y] = playerSign;
    }

    static String geTemplateCoordinates(int fieldSize) {
        StringBuilder template = new StringBuilder();
        for (int i = 1; i <= fieldSize; i ++ ) {
            if (template.length() > 0) {
                template.append(", ");
            }
            template.append(i);
        }
        template.insert(0, "[");
        template.append("]");
        return template.toString();
    }

    static boolean isCellEmpty(char[][] field, int x, int y) {
        return field[x][y] == DOT_EMPTY;
    }

    static void notifyOccupiedCell(boolean isOccupied, int x, int y) {
        if (!isOccupied) {
            System.out.println(String.format("Input coordinates are incorrect. Cell of coordinates [%s, %s] is already occupied", x, y));
        }
    }

    static void notifyIncorrectCoordinates(boolean isCorrect) {
        if (!isCorrect) {
            System.out.println("Input coordinates are incorrect. Available coordinates in range [1, 2, 3]");
        }
    }

    static boolean checkCoordinates(int fieldSize, int x, int y) {
        return (x >= 0 && x <= fieldSize - 1) && (y >= 0 && y <= fieldSize - 1);
    }

    static void drawField(char[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field.length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }

    static char[][] getField(int length) {
        char[][] field = new char[length][length];
        for (int i = 0; i < length; i ++) {
            Arrays.fill(field[i], DOT_EMPTY);
        }
        return  field;
    }

}