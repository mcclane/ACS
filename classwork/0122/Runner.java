import java.util.Scanner;

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

class DLList<E extends Comparable<E>> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    public DLList() {
        head = new Node<E>(null);
        tail = new Node<E>(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }
    private Node<E> getNode(int i) {
        if(i > 0 && i >= size) {
            return null;
        }
        Node<E> current = head.next();
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
        Node<E> current = head;
        int counter = 0;
        while(counter < i) {
            current = current.next();
            counter++;
        }
        if(current.next() == tail) {
            add(data);
        }
        else {
            tba.setNext(current.next());
            tba.setPrev(current);
            current.setNext(tba);
            current.next().setPrev(tba);
            size++;
        }
    }
    public void add(E data) {
        Node<E> tba = new Node<E>(data);
        tail.prev().setNext(tba);
        tba.setPrev(tail.prev());
        tail.setPrev(tba);
        tba.setNext(tail);
        size++;
    }
    public void remove(int i) {
        if(i >= size) {
            return;
        }
        Node<E> tbr = getNode(i);
        tbr.prev().setNext(tbr.next());
        tbr.next().setPrev(tbr.prev());
        size--;
    }
    public void remove(E data) {
        if(size == 0) {
            return;
        }
        Node<E> current = head.next();
        while(current != tail) {
            if(current.get().equals(data)) {
                current.prev().setNext(current.next());
                current.next().setPrev(current.prev());
                size--;
                break;
            }
            current = current.next();
        }
    }
    public void set(int i, E data) {
        System.out.println(getNode(i).prev());
        remove(i);
        add(i, data);
    }
    public String toString() {
        if(size == 0) {
            return "[]";
        }
        String out = "";
        Node<E> current = head.next();
        int time = 1;
        while(current != tail) {
            out += time+". "+current.get()+"\n ";
            current = current.next();
        }
        return out;
    }
    public void print() {
        _print(head.next());
    }
    public void _print(Node<E> n) {
        if(n.get() == null) 
            return;
        else 
            System.out.println(n.get());
            _print(n.next());
    }
/*    public void reverse() {
        for(Node<E> current = dummy.next();current.get() != null;current = current.prev()) {
            Node<E> prev = current.prev();
            current.setPrev(current.next());
            current.setNext(prev);
        }
        Node<E> prev = dummy.prev();
        dummy.setPrev(dummy.next());
        dummy.setNext(prev);
    }
    */
}
public class Runner {
    public static void main(String[] args) {
        DLList<String> dl = new DLList<String>();
        dl.add("Bear");
        dl.add("Bat");
        dl.add("Dog");
        dl.add("Horse");
        dl.add("Rabbit");
//        dl.add(1, "111");
//        dl.add(2, "222");

        dl.set(2, "ASDASFSA");
        dl.print();
    }
}
/*public class Runner {

    static int time = 0;
    static DLList<Song> original = new DLList<Song>();
    static DLList<Song> sorted = new DLList<Song>();

    public static void main(String[] args) {
        original.add(new Song("A", "Ant", 0));
        original.add(new Song("A", "Anteater", 1));
        original.add(new Song("B", "Bear", 2));
        original.add(new Song("C", "Cat", 3));
        original.add(new Song("D", "Duck", 4));
        time = 5;
        sorted = original;
        Scanner sc = new Scanner(System.in);
        String name;
        String artist;
        int index;
        String sortby = "time";
        while(true) {
            System.out.println("1. Add a song\t\t\t2. Display Song List\n3. Delete by name and artist\t4. Delete by index\n5. Delete by artist\t\t6. Delete by name\n7. Sort by artist\t\t8. Sort by name\n9. Sort by time\t\t\t10. Search by artist\n11. Quit");
            int selection = sc.nextInt();
            switch(selection) {
                case 1:
                    System.out.println("Enter in the name");
                    name = sc.next();
                    System.out.println("Enter in the artist");
                    artist = sc.next();
                    add(new Song(name, artist, time), sortby);
                    time++;
                    break;
                case 2:
                    System.out.println(sorted);
                    break;
                case 3:
                    System.out.println("Enter in the name");
                    name = sc.next();
                    System.out.println("Enter in the artist");
                    artist = sc.next();
                    original.remove(new Song(name, artist, 0));
                    sort(sortby);
                    break;
                case 4:
                    System.out.println("Enter in an index");
                    index = sc.nextInt();
                    original.remove(index);
                    sort(sortby);
                    break;
                case 5:
                    System.out.println("Enter in artist");
                    artist = sc.next();
                    for(int i = 0;i < original.size();i++) {
                        if(original.get(i).artist().equals(artist)) {
                            original.remove(i);
                            i--;
                        }
                    }
                    break;
                case 6:
                    System.out.println("Enter in name");
                    name = sc.next();
                    for(int i = 0;i < original.size();i++) {
                        if(original.get(i).name().equals(name)) {
                            original.remove(i);
                            i--;
                        }
                    }
                    break;
                case 7:
                    sortby = "artist";
                    sort(sortby);
                    break;
                case 8:
                    sortby = "name";
                    sort(sortby);
                    break;
                case 9:
                    sortby = "time";
                    sort(sortby);
                    break;
                case 10:
                    System.out.println("Enter artist");
                    artist = sc.next();
                    for(int i = 0;i < original.size();i++) {
                        if(original.get(i).artist().equals(artist)) {
                            System.out.println(original.get(i));
                        }
                    }
                    break;
                case 11:
                    System.exit(0);
                    break;
            }
        }
    }
    public static void add(Song s, String sortby) {
        if(sortby.equals("name")) {
            boolean added = false;
            for(int i = 0;i < original.size();i++) {
                if(original.get(i).name().compareTo(s.name()) > 0) {
                    original.add(i, s);
                    added = true;
                    break;
                }
            }
            if(!added) {
                original.add(s);
            }
        }
        else if(sortby.equals("time")) {
            boolean added = false;
            for(int i = 0;i < original.size();i++) {
                if(original.get(i).time() > s.time()) {
                    original.add(i, s);
                    added = true;
                    break;
                }
            }
            if(!added) {
                original.add(s);
            }
        }
        if(sortby.equals("artist")) {
            boolean added = false;
            for(int i = 0;i < original.size();i++) {
                if(original.get(i).artist().compareTo(s.artist()) > 0) {
                    original.add(i, s);
                    added = true;
                    break;
                }
            }
            if(!added) {
                original.add(s);
            }
        }
    }
    public static void sort(String sortby) {
        if(sortby.equals("name")) {
            sorted = new DLList<Song>();
            for(int i = 0;i < original.size();i++) {
                boolean added = false;
                for(int j = 0;j < sorted.size();j++) {
                    if(sorted.get(j).name().compareTo(original.get(i).name()) > 0) {
                        System.out.println(j);
                        sorted.add(j, original.get(i));
                        added = true;
                        break;
                    }
                }
                if(!added) {
                    sorted.add(original.get(i));
                }
            }
        }
        else if(sortby.equals("artist")) {
            sorted = new DLList<Song>();
            for(int i = 0;i < original.size();i++) {
                boolean added = false;
                for(int j = 0;j < sorted.size();j++) {
                    if(sorted.get(j).artist().compareTo(original.get(i).artist()) > 0) {
                        System.out.println(j);
                        sorted.add(j, original.get(i));
                        added = true;
                        break;
                    }
                }
                if(!added) {
                    sorted.add(original.get(i));
                }
            }
        }
        else {
            sorted = original;
        }
        original = sorted;
    }
}*/

