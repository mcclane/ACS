class Node<T> {
    T data;
    Node<T> next;
    public Node(T data) {
        this.data = data;
        next = null;
    }
    public void setNext(Node<T> next) {
        this.next = next;
    }
    public T getData() {
        return data;
    }
    public Node<T> getNext() {
        return next;
    }
}
class SLList<T> {
    private int size;
    Node<T> head;
    public SLList() {
        head = null;
    }
    public void add(T data) {
        size++;
        if(head == null) {
            head = new Node<T>(data);
            return;
        }
        Node<T> current = head;
        while(current.getNext() != null) {
            current = current.getNext();
        }
        current.setNext(new Node<T>(data));
    }
    public String toString() {
        Node<T> current = head;
        String out = "[";
        while(current != null) {
            out += current.getData()+", ";
            current = current.getNext();
        }
        out = out.substring(0, out.length()-2);
        return out+"]";
    }
    public boolean contains(T data) {
        Node<T> current = head;
        while(current != null) {
            if(current.getData().equals(data)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }
    public void remove(T data) {
        if(head.getData().equals(data)) {
            head = head.getNext();
            size--;
            return;
        }
        Node<T> previous = head;
        Node<T> current = head.getNext();
        while(current != null) {
            if(current.getData().equals(data)) {
                previous.setNext(current.getNext());
                size--;
                break;
            }
            previous = current;
            current = current.getNext();
        }
    }
    public T get(int index) {
        int currentIndex = 0;
        Node<T> current = head;
        while(currentIndex < index && current.getNext() != null) {
            current = current.getNext();
            currentIndex++;
        }
        if(currentIndex < index) {
            throw new IndexOutOfBoundsException("no.");
        }
        return current.getData();
    }
    public int indexOf(T data) {
        int index = 0;
        Node<T> current = head;
        while(current != null) {
            if(current.getData().equals(data)) {
                return index;
            }
            current = current.getNext();
            index++;
        }
        return -1;
    }
    public void set(int index, T data) {
        if(index == 0) {
            Node<T> newNode = new Node<T>(data);
            newNode.setNext(head.getNext());
            head = newNode;
            return;
        }
        int counter = 1;
        Node<T> previous = head;
        Node<T> current = head.getNext();
        while(current != null) {
            System.out.println(counter+" adsf");
            if(counter == index) {
                System.out.println("set");
                Node<T> newNode = new Node<T>(data);
                previous.setNext(newNode);
                newNode.setNext(current.getNext());
                return;
            }
            previous = current;
            current = current.getNext();
            counter++;
        }
    }
    public int size() {
        return size;
    }
    public void add(int index, T data) {
        if(index >= size) {
            return;
        }
        Node<T> newNode = new Node<T>(data);
        if(index == 0) {
            newNode.setNext(head);
            head = newNode;
            size++;
            return;
        }
        int counter = 0;
        Node<T> current = head;
        while(index-1 > counter && current.getNext() != null) {
            current = current.getNext();
            counter++;
        }

        newNode.setNext(current.getNext());
        current.setNext(newNode);
        size++;
    }
}
