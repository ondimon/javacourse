public class Main {

    public static void main(String[] args) {
        // Создать переменные типов: byte, short, int, long, float, double, char, boolean;
        // Создать переменные всех пройденных типов данных, и инициализировать их значения;

        byte b = 1;
        short s  = 2;
        int i = 3;
        long l = 1000L;
        float f = 3.14f;
        double d = 1.56789;
        char c = 'f';
        boolean flag = false;

        // task 3
        System.out.println("task 3");
        task3(1, 2,4,2);
        delimiter();

        // task 4
        System.out.println("task 4");
        task4(5, 5);
        task4(10, 10);
        task4(10, 5);
        task4(1, -2);
        task4(25, -2);
        delimiter();

        // task 5
        System.out.println("task 5");
        isPositiveOrNegative(5);
        isPositiveOrNegative(0);
        isPositiveOrNegative(-5);
        delimiter();

        // task 6
        System.out.println("task 6");
        task6(-5);
        task6(5);
        delimiter();

        // task 7
        System.out.println("task 7");
        hello("Dimon");
        delimiter();

        // task 8
        System.out.println("task 8");
        isLeapYear(1700);
        isLeapYear(2000);
        isLeapYear(2016);
        isLeapYear(2020);
        isLeapYear(2300);
    }

    // Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,
    // где a, b, c, d – входные параметры этого метода
    public static void task3(int a, int b, int c, int d) {
        System.out.printf("%d * (%d + (%d / %d)) = ", a, b, c, d);
        System.out.println(calc(a, b, c, d));
    }
    public static int calc(int a, int b, int c, int d) {
        return a * (b + (c / d));
    }

    // Написать метод, принимающий на вход два числа,
    // и проверяющий что их сумма лежит в пределах от 10 до 20(включительно),
    // если да – вернуть true, в противном случае – false;

    public static void task4(int a, int b) {
        System.out.printf("is %d + %d from 10 to 20: ", a, b);
        System.out.println(sumFrom10To20(a, b));
    }

    public static boolean sumFrom10To20(int a, int b){
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    // Написать метод, которому в качестве параметра передается целое число,
    // метод должен напечатать в консоль положительное ли число передали, или отрицательное;
    // Замечание: ноль считаем положительным числом.
    public static void isPositiveOrNegative(int digit) {
        String result;
        result = digit >= 0 ? "Positive" : "Negative";
        System.out.printf("%d is %b\n\r", digit, result);
    }

    // Написать метод, которому в качестве параметра передается целое число,
    // метод должен вернуть true, если число отрицательное;

    public static void task6(int digit) {
        System.out.printf("is %d negative: ", digit);
        System.out.println(isNegative(digit));
    }

    public static boolean isNegative(int digit) {
        return (digit < 0);
    }

    // Написать метод, которому в качестве параметра передается строка,
    // обозначающая имя, метод должен вывести в консоль сообщение «Привет, указанное_имя!»;
    public static void hello(String name) {
        System.out.printf("Привет, %s!\n\r", name);
    }

    // Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль.
    // Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный
    public static void isLeapYear(int year) {
        boolean result = false;

        if(year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
            result = true;
        }

        System.out.printf("is %d leap year: %b\n\r", year, result);
    }

    public static void delimiter() {
        System.out.println("===========================================================");
        System.out.println();
    }
}
