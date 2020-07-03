import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoDirectionalList implements DirectionalList {
    private int size;
    private Node first;
    private Node last;

    public TwoDirectionalList() {

    }

    public TwoDirectionalList(String[] array) {
        for(String val : array) {
            add(val);
        }
    }

    @Override
    public void add(String val) {
        if (last == null) {
            last = new Node(val, null, null);
            first = last;
        }else{
            Node newLast = new Node(val, last, null);
            last.setNext(newLast);
            last = newLast;
        }
        size++;
    }

    @Override
    public boolean remove(String val) {
        if(first == null) {
            return false;
        }

        for(Node i = first; i != null; i = i.getNext()) {
            if(i.getVal().equals(val)) {
                unlink(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public String getFirst() {
        if(first == null) {
            throw new NoSuchElementException();
        }
        return first.getVal();
    }

    public String getLast() {
        if(last == null) {
            throw new NoSuchElementException();
        }
        return last.getVal();
    }

    @Override
    public int size() {
        return size;
    }

    private void unlink(Node item) {
        Node next = item.getNext();
        Node prev = item.getPrev();

        if(next == null) {
            last = prev;
        }
        if(prev == null) {
            first = next;
        }

        prev.setNext(next);
        next.setPrev(prev);
        size --;

    }

    @Override
    public Iterator<String> iterator() {
        return new IterNode(first);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Iterator<String> it = this.iterator();
        while(it.hasNext()) {
            sb.append(it.next());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    private class Node  {
        private String val;
        private Node next;
        private Node prev;

        private Node(String val, Node prev, Node next) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }

        private String getVal() {
            return val;
        }

        private Node getNext() {
            return next;
        }

        private Node getPrev() {
            return prev;
        }

        private void setNext(Node next) {
            this.next = next;
        }

        private void setPrev(Node prev) {
            this.prev = prev;
        }

    }

    private class IterNode implements Iterator<String> {
        private Node node;

        public IterNode(Node node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public String next() {
            String val = node.getVal();
            node = node.getNext();
            return val;
        }
    }
}
