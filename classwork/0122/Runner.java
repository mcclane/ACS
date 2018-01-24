import java.util.Scanner;

class Task implements Comparable<Task> {
    String name;
    int rank;
    public Task(int rank, String name) {
        this.name = name;
        this.rank = rank;
    }
    public boolean equals(Object o) {
        Task t = (Task) o;
        return toString().equals(t.toString());
    }
    public int compareTo(Task t) {
        return toString().compareTo(t.toString());
    }
    public String toString() {
        return rank+" "+name+"\n";
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
/*    public void add(int i, E data) {
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
    }*/
    public void add(E a)
	{
	  Node<E> newNode=new Node<E>(a);
	  if(size==0)
	  {
  		Node<E> before=tail.prev();
  		before.setNext(newNode);
  		tail.setPrev(newNode);
  		newNode.setNext(tail);
  		newNode.setPrev(before);
	  }
	  else
	  {
  	  Node<E> curr=head.next();
  	  while(curr!=tail)
  	  {
  	    if(curr.get().equals(a)==true)
  	    {
  	      return;
  	    }
  	    if(curr.get().compareTo(a)>0)
  	    {
  	      break;
  	    }
  	    curr=curr.next();
  	  }
  	  Node<E> before=curr.prev();
  		before.setNext(newNode);
  		curr.setPrev(newNode);
  		newNode.setNext(curr);
  		newNode.setPrev(before);
	  }
	  
		/*Node<E> newNode=new Node<E>(a);
		Node<E> before=tail.prev();
		before.setNext(newNode);
		tail.setPrev(newNode);
		newNode.setNext(tail);
		newNode.setPrev(before);*/
		size++;
	}
/*public int indexOf(E a)
  {
    Node<E> curr=head.next();
    int index=0;
    while(index<size)
    {
      if(this.get(index).equals(a))
      {
        return(index);
      }
      index++;
    }
    return(index);
  }
    */public void remove(int i) {
        if(i >= size) {
            return;
        }
        Node<E> tbr = getNode(i);
        tbr.prev().setNext(tbr.next());
        tbr.next().setPrev(tbr.prev());
        size--;
    }/*
public void remove(E a)
  {
    this.remove(indexOf(a));
    size--;
  }*/
    
    public void remove(E data) {
        Node<E> tbr = new Node<E>(data);
        System.out.println(tbr.get());
        for(Node<E> current = head.next(); current != tail; current = current.next()) {
            if(current.get().equals(tbr.get())) {
                System.out.println("REemoveing "+current.get());
                Node<E> temp = current.prev();
                current.prev().setNext(current.next());
                current.next().setPrev(temp);
                size--;
                break;
            }
        }
    }
    public void set(int i, E data) {
        System.out.println(getNode(i).prev());
        remove(i);
        add(data);
    }
    public String toString() {
        if(size == 0) {
            return "[]";
        }
        String out = "";
        Node<E> current = head.next();
        int time = 1;
        while(current != tail) {
            out += current.get();
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
}
public class Runner {
    public static void main(String[] args) {
        DLList<Task> tasks = new DLList<Task>();
        tasks.add(new Task(1, "Drink Water"));
        tasks.add(new Task(2, "Find Shelter"));
        tasks.add(new Task(3, "Eat food"));
        tasks.add(new Task(4, "Make Friends"));
        tasks.add(new Task(4, "Self Actualization"));
        System.out.println(tasks);
        Scanner sc = new Scanner(System.in);
        int input;
        int rank;
        String name;
        while(true) {
            System.out.println("1. Display\t\t2. Add a task\t\t3. Remove Task\t\t4. Update Task"); 
            input = sc.nextInt();
            if(input == 1) {
                System.out.println(tasks);
            }
            else if(input == 2) {
                System.out.println("Enter rank");
                rank = sc.nextInt();
                System.out.println("Enter name");
                sc.nextLine();
                name = sc.nextLine();
                Task t = new Task(rank, name);
                tasks.add(t);
                /*boolean added = false;
                for(int i = 0;i < tasks.size();i++) {
                    if(tasks.get(i).equals(t)) {
                        added = true;
                        break;
                    }
                    else if(tasks.get(i).compareTo(t) > 0) {
                        tasks.add(i+1, t);
                        added = true;
                        break;
                    }
                }
                if(!added) {
                    tasks.add(t);
                }*/
            }
            else if(input == 3) {
                System.out.println("Enter rank");
                rank = sc.nextInt();
                System.out.println("Enter name");
                sc.nextLine();
                name = sc.nextLine();
                Task t = new Task(rank, name);
                tasks.remove(t);
            }
            else if(input == 4) {
                System.out.println("Enter rank");
                rank = sc.nextInt();
                System.out.println("Enter name");
                sc.nextLine();
                name = sc.nextLine();
                System.out.println("Enter a new rank");
                int newRank = sc.nextInt();
                System.out.println("Enter a new name");
                sc.nextLine();
                String newName = sc.nextLine();
                Task tr = new Task(rank, name);
                Task t = new Task(newRank, newName);
                tasks.remove(tr);
                tasks.add(t);
/*                boolean added = false;
                for(int i = 0;i < tasks.size();i++) {
                    if(tasks.get(i).equals(t)) {
                        added = true;
                        break;
                    }
                    else if(tasks.get(i).compareTo(t) > 0) {
                        tasks.add(i+1, t);
                        added = true;
                        break;
                    }
                }
                if(!added) {
                    tasks.add(t);
                }
                */
            }
        }
    }
}
