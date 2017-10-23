import java.util.Stack;
import java.util.Iterator;

class Box {
    String name;
    double weight;
    public Box(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }
    public String toString() {
        return name+" "+weight+"lbs";
    }
}
public class WarmUp {
    public static void main(String[] args) {
        Stack<Box> s = new Stack<Box>();
        s.push(new Box("Shoes", 2.5));
        s.push(new Box("Speakers", 4.1));
        s.push(new Box("Cups", 3));
        double totalWeight = 0;
        Iterator<Box> temp = s.iterator();
        while(temp.hasNext()) {
            Box curr = temp.next();
            totalWeight += curr.weight;
            System.out.println(curr);
        }
        System.out.println(totalWeight);
        s.pop();
        temp = s.iterator();
        totalWeight = 0;
        while(temp.hasNext()) {
            Box curr = temp.next();
            totalWeight += curr.weight;
            System.out.println(curr);
        }
        System.out.println(totalWeight);
        
    }
}