public class DLList<E> {
    private Node<E> head;
    private Node<E> tail;
    
    private int size;
    
    public DLList() {
        size = 0;
        head = new Node<E>(null);
        tail = new Node<E>(null);
        head.setNext(tail);
        tail.setPrev(head);
        head.setPrev(tail);
        tail.setPrev(head);
    }
    public Node<E> getNode(E data) {
        Node<E> nodeToSearchFor = new Node<E>(data);
        for(Node<E> current = head.next();current != tail;current = current.next()) {
            if(current.get().equals(nodeToSearchFor.get())) {
                return current;
            }
        }
        return null;
    }
    public void add(E data) {
        Node<E> nodeToAdd = new Node<E>(data);
        nodeToAdd.setPrev(tail.prev());
        nodeToAdd.setNext(tail);
        tail.prev().setNext(nodeToAdd);
        tail.setPrev(nodeToAdd);
        size++;
    }
    public void remove(E data) {
        Node<E> n = getNode(data);
        if(n != null) {
            n.prev().setNext(n.next());
            n.next().setPrev(n.prev());
            size--;
        }
    }
    public String toString() {
        String out = "";
        for(Node<E> current = head.next();current != tail;current = current.next()) {
            out += current.get().toString()+", ";
        }
        return out;
    }
    public int size() {
        return size;
    }
}