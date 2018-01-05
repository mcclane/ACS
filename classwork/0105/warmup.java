import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

class Profile {
    String name;
    int age;
    public Profile(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String toString() {
        return name+" "+age+"\n";
    }
}

public class warmup {
    public static void main(String[] args) {
        LinkedList<Profile> ll = new LinkedList<Profile>();
        ll.add(new Profile("Jennifer", 17));
        ll.add(new Profile("John", 16));
        ll.add(new Profile("Jose", 17));
        System.out.println(ll);
        Scanner sc = new Scanner(System.in);
        System.out.println("enter a name to delete");
        String name = sc.next();
        Iterator<Profile> it = ll.iterator();
        while(it.hasNext()) {
            Profile curr = it.next();
            if(curr.name.equals(name)) {
                ll.remove(curr);
                break;
            }
        }
        System.out.println(ll);
    }
}
