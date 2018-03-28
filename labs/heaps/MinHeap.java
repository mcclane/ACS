import java.util.LinkedList;

class MinHeap<E extends Comparable<E>> {
    private LinkedList<E> list;
    public MinHeap() {
        list = new LinkedList<E>();
    }
    public void add(E data) {
        list.add(data);
        swapUp();
    }
    public void swapUp() {
        int bot = list.size()-1;
        while( bot>0 ){
            int parent = (bot-1)/2;
            if (list.get(parent).compareTo(list.get(bot)) > 0) {
                E temp = list.get(parent);
                list.set(parent, list.get(bot));
                list.set(bot, temp);
                bot = parent;
            }
           else {
                break; 
           }
        }

    }
    public void remove(E data) {
        int i = list.indexOf(data);
        if(i != -1) {
            list.remove(data);
            swapDown(i);
        }
    }
    public void swapDown(int parent) {
        int left = parent*2+1;
        int right = parent*2+2;
        if(left < list.size()) {
            if(right < list.size()) {
                //both children exist
                if(list.get(left).compareTo(list.get(right)) < 0) {
                    if(list.get(left).compareTo(list.get(parent)) < 0) {
                        E temp = list.get(left);
                        list.set(left, list.get(parent));
                        list.set(parent, temp);
                        swapDown(left);
                    }
                }
                else if(list.get(right).compareTo(list.get(left)) < 0){
                    if(list.get(right).compareTo(list.get(parent)) < 0) {
                        E temp = list.get(right);
                        list.set(right, list.get(parent));
                        list.set(parent, temp);
                        swapDown(right);
                    }
                }
            }
            //left child exists, always left child
            if(list.get(left).compareTo(list.get(parent)) < 0) {
                E temp = list.get(left);
                list.set(left, list.get(parent));
                list.set(parent, temp);
                //swapDown(left);
            }
            
        }
    }
    public E peek() {
        if(list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
    public E poll() {
        E root = list.poll();
        swapDown(0);
        return root;
    }
    public String toString() {
        String out = "";
        for(int i = 1;i < list.size();i *= 2) {
            for(int j = i-1;j < (i*2)-1;j++) {
                if(j >= list.size())
                    break;
                out += list.get(j)+"\t";
            }
            out += "\n";
        }
        return out;
    }
    public int size() {
        return list.size();
    }
}