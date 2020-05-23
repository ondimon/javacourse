public class Main {

    public static void main(String[] args) {
        doLesson5();
    }

    public static void doLesson5() {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Ivanov Ivan", "Boss", "bos@company.com", "81111111", 10000, 30);
        employees[1] = new Employee("Petrov Petr", "Watchman", "petr@company.com", "82222222", 1000, 60);
        employees[2] = new Employee("Vasilev Ivan", "Engineer", "ivan@company.com", "8333333", 10000, 40);
        employees[3] = new Employee("Andreev Andrey", "Engineer", "andrey@company.com", "84444444", 10000, 45);
        employees[4] = new Employee("Alekseev Aleksey", "Engineer", "aleksey@company.com", "85555555", 10000, 25);

        for ( Employee employee : employees ) {
            if (employee.getAge() > 40) {
                employee.printCard();
            }
        }
    }

}
