import java.util.Stack;

public class warmup {
    public static void main(String[] args) {
        Stack<Integer> s = new Stack<Integer>();
        for(int i = 1;i < 10;i++) {
            s.push(i);
        }
        System.out.println(s);
        Stack<Integer> s1 = new Stack<Integer>();
        for(int i = 0;i < 5;i++) {
            s1.push(s.pop());
        }
        System.out.println(s1);
    }
}