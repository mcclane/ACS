import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;

public class Exam1 {
    private ArrayList<Item<Profile, Integer>> itemList = new ArrayList<Item<Profile, Integer>>();
    private ArrayList<Profile> profileList = new ArrayList<Profile>();
    private HashSet<Profile> hashList = new HashSet<Profile>();
    public Exam1() {}
    public void addItemList(String name, int year, int n) {
        itemList.add(new Item<Profile, Integer>(new Profile(name, year), n));
    }
    public void printItemList() {
        Iterator it = itemList.iterator();
        while(it.hasNext()) {
            System.out.print(" "+it.next());
        }
    }
    public void addProfileList(String name, int year) {
        ListIterator<Profile> it = profileList.listIterator();
        boolean added = false;
        while(it.hasNext()) {
            if(it.next().getBirthYear() > year) {
                it.previous();
                it.add(new Profile(name, year));
                added = true;
                break;
            }
        }
        if(added == false) {
            profileList.add(new Profile(name, year));
        }
    }
    public void printProfileList() {
        Iterator<Profile> it = profileList.iterator();
        while(it.hasNext()) {
            System.out.print(" "+it.next());
        }
    }
    public void addHashList(String name, int age) {
        hashList.add(new Profile(name, age));
    }
    public void printHashList() {
        Iterator<Profile> it = hashList.iterator();
        while(it.hasNext()) {
            System.out.print(" "+it.next());
        }
    }
}