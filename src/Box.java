import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> storage = new ArrayList<>();

    public void add(T fruit) {
        storage.add(fruit);
    }

    public float getWeight() {
        float result = 0.0f;
        for( T fruit : storage) {
            result += fruit.getWeight();
        }
        return result;
    }
    public boolean compare(Box<?> box) {
        return this.getWeight() == box.getWeight();
    }
    public T getFruit() {
        if (isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T fruit = storage.get(0);
        storage.remove(0);
        return fruit;
    }
    public boolean isEmpty(){
        return storage.size() == 0;
    }

    public void moveTo(Box<T> box) {
        while (!this.isEmpty()) {
            box.add(this.getFruit());
        }
    }

    @Override
    public String toString() {
        return storage.toString();
    }
}
