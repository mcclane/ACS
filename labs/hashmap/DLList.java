public class DLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    public DLList() {
        head = new Node<E>(null);
        tail = new Node<E>(null);
        tail.setPrev(head);
        head.setNext(tail);
    }
    public void add(E data) {
        Node<E> newNode = new Node<E>(data);
        Node<E> temp = tail.prev();
        tail.setPrev(newNode);
        temp.setNext(newNode);
        newNode.setPrev(temp);
        newNode.setNext(tail);
        size++;
    }
    public E get(int i) {
        int counter = 0;
        for(Node<E> current = head.next();current != tail;current = current.next()) {
            if(counter == i) {
                return current.get();
            }
            counter++;
        }
        return null;
    }
    public boolean contains(E data) {
        for(Node<E> current = head.next();current != tail;current = current.next()) {
            if(current.get().equals(data)) {
                return true;
            }
        } 
        return false;
    }
  public void remove(E data) {
  	  for(Node<E> current = head.next();current != tail;current = current.next()) {
        if(current.get().equals(data)) {
          			current.prev().setNext(current.next());
          			current.next().setPrev(current.prev());
          			size--;
                return;
        }
     }
  }
    public void add(int i, E data) {
        int counter = 0;
        for(Node<E> current = head.next();current != tail;current = current.next()) {
            if(counter == i) {
                Node<E> newNode = new Node<E>(data);
                Node<E> temp = current.prev();
                current.setPrev(newNode);
                temp.setNext(newNode);
                newNode.setPrev(temp);
                newNode.setNext(current);
                size++;
                return;
            }
            counter++;
        }
        //otherwise add at the end
        add(data);
    }
    public int size() {
        return size;
    }
    public String toString() {
        String out = "";
        for(Node<E> current = head.next();current != tail;current = current.next()) {
            out += " "+current.get().toString();
        }
        return out;
    }
}