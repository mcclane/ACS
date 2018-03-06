public class Runner {
    public static void main(String[] args) {
        HashTable<Profile> ht = new HashTable<Profile>();
        ht.add(new Profile(2010, "Jennifer"));
        ht.add(new Profile(2011, "Jose"));
        ht.add(new Profile(2001, "Jane"));
        ht.add(new Profile(1980, "John"));
        ht.add(new Profile(1971, "Jason"));
        ht.add(new Profile(1978, "Javier"));
        ht.add(new Profile(1977, "Julia"));
        System.out.println(ht+"\n");
        ht.remove(new Profile(1977, "Julia"));
        System.out.println(ht+"\n");
        ht.remove(new Profile(2001, "Jane"));
        System.out.println(ht+"\n");
    }
}