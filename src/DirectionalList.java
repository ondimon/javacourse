public interface DirectionalList extends Iterable<String>{
    void add(String val);
    boolean remove(String val);
    Object getFirst();
    int size();
}
