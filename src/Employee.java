public class Employee {
    private String fullName;
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Employee(String fullName, String position, String email, String phone, int salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public void printCard() {
        System.out.println("===========================================================");
        System.out.println(String.format("Full name: %s", fullName));
        System.out.println(String.format("Age: %d", age));
        System.out.println(String.format("Position: %s", position));
        System.out.println(String.format("Email: %s", email));
        System.out.println(String.format("Phone: %s", phone));
        System.out.println(String.format("Salary: %d", salary));
        System.out.println("===========================================================");
    }
}
