import java.util.PriorityQueue;
import java.util.Stack;
import java.util.HashMap;

public class Exam2 {
    private PriorityQueue<Profile> profileList = new PriorityQueue<Profile>();
    public double postFix(String[] exp) {
        Stack<Double> s = new Stack<Double>();
        double first, second;
        for(String l : exp) {
            switch(l) {
                case "+":
                    first = s.pop();
                    second = s.pop();
                    s.push(first+second);
                    break;
                case "-":
                    first = s.pop();
                    second = s.pop();
                    s.push(first-second);
                    break;
                case "*":
                    first = s.pop();
                    second = s.pop();
                    s.push(first*second);
                    break;
                case "/":
                    first = s.pop();
                    second = s.pop();
                    s.push(first/second);
                    break;
                default:
                    s.push(Double.parseDouble(l));
                    break;
            }
        }
        return s.pop();
    }
    public void addProfile(String name, int age) {
        profileList.add(new Profile(name, age));
    }
    public void removeTop() {
        System.out.println(profileList.remove());
    }
    public void peekTop() {
        System.out.println(profileList.peek());
    }
    public void printProfiles() {
        PriorityQueue<Profile> temp = new PriorityQueue<Profile>();
        while(!profileList.isEmpty()) {
            Profile curr = profileList.poll();
            System.out.println(curr);
            temp.add(curr);
        }
        profileList = temp;
    }
}