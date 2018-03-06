public class HashTable<E> {
    private DLList<E>[] table;
    
    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new DLList[100];
    }
    public void add(E data) {
        int i = data.hashCode()%table.length;
        if(table[i] == null) {
            table[i] = new DLList<E>();
        }
        table[i].add(data);
    }
    public String toString() {
        String out = "";
        for(int i = 0;i < table.length;i++) {
            if(table[i] != null && table[i].size() > 0) {
                out += "Bucket #"+i+" - "+table[i].toString()+"\n";
            }
        }
        return out;
    }
    public void remove(E data) {
        int i = data.hashCode()%table.length;
        if(table[i] != null) {
            table[i].remove(data);
        }
    }
}