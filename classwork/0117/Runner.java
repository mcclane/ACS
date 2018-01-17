import java.util.Scanner;

class Song {
  private String artist;
  private String name;
  public Song(String a,String b)
  {
    artist=a;
    name=b;
  }
  
  public boolean equals(Object o)
  {
    Song temp=(Song)o;
    if(this.toString().equals(temp.toString()))
    {
      return(true);
    }
    return(false);
  }
  
  public String toString()
  {
    return(artist+": "+name);
  }
  public String artist() {
      return artist;
  }
  public String name() {
      return name;
  }
}
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
        String out = "[";
        Node<E> current = dummy.next();
        while(current != dummy) {
            out += current.get()+", ";
            current = current.next();
        }
        out = out.substring(0, out.length()-2) + "]";
        return out;
    }
}

public class Runner {

    static DLList<Song> original = new DLList<Song>();
    static DLList<Song> sorted = new DLList<Song>();

    public static void main(String[] args) {
        original.add(new Song("A", "Ant"));
        original.add(new Song("A", "Anteater"));
        original.add(new Song("B", "Bear"));
        original.add(new Song("C", "Cat"));
        original.add(new Song("D", "Duck"));
        sorted = original;
        Scanner sc = new Scanner(System.in);
        String name;
        String artist;
        int index;
        String sortby = "time";
        while(true) {
            System.out.println("1. Add a song\t\t\t2. Display Song List\n3. Delete by name and artist\t4. Delete by index\n5. Delete by artist\t\t6. Delete by name\n7. Sort by artist\t\t8. Sort by name\n9. Sort by time\t\t\t10. Search by artist\n11. Quit");
            System.out.println(sorted);
            int selection = sc.nextInt();
            switch(selection) {
                case 1:
                    System.out.println("Enter in the name");
                    name = sc.next();
                    System.out.println("Enter in the artist");
                    artist = sc.next();
                    original.add(new Song(name, artist));
                    sort(sortby);
                    break;
                case 2:
                    System.out.println(sorted);
                    break;
                case 3:
                    System.out.println("Enter in the name");
                    name = sc.next();
                    System.out.println("Enter in the artist");
                    artist = sc.next();
                    original.remove(new Song(name, artist));
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
                    sort(sortby);
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
                    sort(sortby);
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
    }
}
