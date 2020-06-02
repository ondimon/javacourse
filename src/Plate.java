public class Plate {

    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public boolean checkFood(int n) {
        return food >= n;
    }

    public boolean decreaseFood(int n) {
        if( n > food) {
            return false;
        } else {
            food -= n;
            return true;
        }
    }

    public void increaseFood(int n) {
        food += n;
    }

    public void info() {
        System.out.println("plate: " + food);
    }
}