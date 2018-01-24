class Node<E> {
    private Node<E> previous;
    private Node<E> next;
    private E data;
    public Node(E data) {
        this.data = data;
        previous = null;
        next = null;
    }
    public void setNext(Node<E> next) {
        this.next = next;
    }
    public void setPrev(Node<E> previous) {
        this.previous = previous;
    }
    public E get() {
        return data;
    }
    public Node<E> next() {
        return next;
    }
    public Node<E> prev() {
        return previous;
    }
}

class DLList<E> {
    private Node<E> dummy;
    private int size;
    public DLList() {
        dummy = new Node<E>(null);
        size = 0;
    }
    private Node<E> getNode(int i) {
        if(i > 0 && i >= size) {
            return null;
        }
        Node<E> current = dummy;
        int counter = 0;
        while(counter < i) {
            current = current.next();
            counter++;
        }
        return current.next();
    }
    public E get(int i) {
        return getNode(i).get();
    }
    public int size() {
        return size;
    }
    public void add(int i, E data) {
        if(i > 0 && i >= size) {
            return;
        }
        Node<E> tba = new Node<E>(data);
        Node<E> current = dummy;
        int counter = 0;
        while(counter < i) {
            current = current.next();
            counter++;
        }
        if(current.next() == null) {
            add(data);
        }
        else {
            size++;
            current.next().setPrev(tba);
            tba.setNext(current.next());
            tba.setPrev(current);
            current.setNext(tba);
        }
    }
    public void add(E data) {
        size++;
        Node<E> tba = new Node<E>(data);
        if(dummy.prev() == null) {
            dummy.setNext(tba);
            dummy.setPrev(tba);
            tba.setPrev(dummy);
            tba.setNext(dummy);
        }
        else {
            dummy.prev().setNext(tba);
            tba.setPrev(dummy.prev());
            dummy.setPrev(tba);
            tba.setNext(dummy);
        }
    }
    public E remove(int i) {
        Node<E> tbr = getNode(i);
        tbr.prev().setNext(tbr.next());
        tbr.next().setPrev(tbr.prev());
        size--;
        return tbr.get();
    }
    public void remove(E data) {
        if(size == 0) {
            return;
        }
        Node<E> current = dummy.next();
        while(current.get() != null) {
            if(current.get().equals(data)) {
                current.prev().setNext(current.next());
                current.next().setPrev(current.prev());
                size--;
                break;
            }
            current = current.next();
        }
    }
    public String toString() {
        if(size == 0) {
            return "[]";
        }
        String out = "";
        Node<E> current = dummy.next();
        int time = 1;
        while(current != dummy) {
            out += current.get();
            current = current.next();
        }
        return out;
    }
    public void print() {
        _print(dummy.next());
    }
    public void _print(Node<E> n) {
        if(n.get() == null) 
            return;
        else 
            System.out.println(n.get());
            _print(n.next());
    }
    public void reverse() {
        for(Node<E> current = dummy.next();current.get() != null;current = current.prev()) {
            Node<E> prev = current.prev();
            current.setPrev(current.next());
            current.setNext(prev);
        }
        Node<E> prev = dummy.prev();
        dummy.setPrev(dummy.next());
        dummy.setNext(prev);
    }
}
