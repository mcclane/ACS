import java.util.LinkedList;
import java.util.Scanner;

class Task implements Comparable<Task> {
    public String name;
    public int priority;
    public Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
    public String toString() {
        return name+"\t"+priority;
    }
    public int compareTo(Task t) {
        return priority - t.priority;
    }
}

class MaxHeap<E extends Comparable<E>> {
    private int size;
    private E[] list;
    @SuppressWarnings("unchecked")
    public MaxHeap() {
        size = 0;
        list = (E[])new Comparable[1000];
    }
    public void add(E data) {
        list[size] = data;
        size++;
        swapUp();
    }
    public void swapUp() {
        int bot = size-1;
        while( bot>0 ){
            int parent = (bot-1)/2;
            if (list[parent].compareTo(list[bot]) < 0) {
                E temp = list[parent];
                list[parent] = list[bot];
                list[bot] = temp;
                bot = parent;
            }
           else {
                break; 
           }
        }

    }
    public void swapDown(int parent) {
        int left = parent*2+1;
        int right = parent*2+2;
        if(left < size) {
            if(right < size) {
                //both children exist
                if(list[left].compareTo(list[right]) > 0) {
                    if(list[left].compareTo(list[parent]) > 0) {
                        E temp = list[left];
                        list[left] = list[parent];
                        list[parent] = temp;
                        swapDown(left);
                    }
                }
                else if(list[right].compareTo(list[left]) > 0){
                    if(list[right].compareTo(list[parent]) > 0) {
                        E temp = list[right];
                        list[right] = list[parent];
                        list[parent] = temp;
                        swapDown(right);
                    }
                }
            }
            //left child exists, always left child
            if(list[left].compareTo(list[parent]) > 0) {
                E temp = list[left];
                list[left] = list[parent];
                list[parent] = temp;
                //swapDown(left);
            }
            
        }
    }
    public E poll() {
        E root = list[0];
        for(int i = 1;i < size;i++) {
            list[i-1] = list[i];
        }
        size--;
        swapDown(0);
        return root;
    }
    public String toString() {
        String out = "";
        for(int i = 1;i < size;i *= 2) {
            for(int j = i-1;j < (i*2)-1;j++) {
                if(j >= size)
                    break;
                out += list[j]+"\t";
            }
            out += "\n";
        }
        return out;
    }
    public int size() {
        return size;
    }
}

public class classwork {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MaxHeap<Integer> m = new MaxHeap<Integer>();
        for(int i = 0;i < 8;i++) {
            m.add((int)(Math.random()*100+1));
        }
        System.out.println(m);
        while(m.size() > 0) {
            System.out.println(m.poll());
        }
    }
}