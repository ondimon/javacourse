import java.util.Iterator;

public class OneDirectionalList implements DirectionalList {
    private Node first;

    public OneDirectionalList() {

    }

    public OneDirectionalList(String[] array) {
        for(String val : array) {
            add(val);
        }
    }

    @Override
    public void add(String val) {
        if (first == null) {
            first = new Node(val, null);
            return;
        }

        add(val, first);
    }

    @Override
    public boolean remove(String val) {
        if (first.getVal().equals(val)) {
            if (first.getNext() == null) {
                first = null;
            } else {
                /**
                 * Будьте внимательные происходит замена ссылки
                 * first уже не будет прежним
                 */
                first = first.getNext();
            }
            return true;
        }

        Node current = first.getNext();
        Node prev = first;

        while (current != null) {
            if (current.getVal().equals(val)) {
                prev.setNext(current.getNext());
                return true;
            } else {
                prev = current;
                current = current.getNext();
            }
        }

        return false;
    }

    @Override
    public Node getFirst() {
        return first;
    }

    @Override
    public int size() {
        if (first == null) {
            return 0;
        }

        int size = 1;

        Node current = first;
        while (current.getNext() != null) {
            size++;
            current = current.getNext();
        }
        return size;
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

    private void add(String val, Node current) {
        if (current.getNext() == null) {
            current.setNext(new Node(val, null));
        } else {
            add(val, current.getNext());
        }
    }

    public class Node {
        private String val;
        private Node next;

        public Node(String val, Node next) {
            this.val = val;
            this.next = next;
        }

        public String getVal() {
            return val;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
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
