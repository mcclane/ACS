import java.util.LinkedList;

public class MaxHeap<E extends Comparable<E>> {
    private LinkedList<E> myList;
    public MaxHeap() {
        myList = new LinkedList<E>();
    }
    public void add(E data) {
        myList.add(data);
        int bot = myList.size()-1;
        while(bot > 0) {
            int parent = (bot-1)/2;
            if(myList.get(bot).compareTo(myList.get(parent)) > 0) {
                E temp = myList.get(bot);
                myList.set(bot, myList.get(parent));
                myList.set(parent, temp);
                bot = parent;
            }
            else {
                break;
            }
        }
    }
    public String toString() {
        String out = "";
        for(int i = 1;i < myList.size()+1;i *= 2) {
            for(int j = i-1;j < (i*2)-1;j++) {
                if(j >= myList.size())
                    break;
                out += myList.get(j)+" ";
            }
            out += "\n";
        }
        return out;
    }
}