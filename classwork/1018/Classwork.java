import java.util.Stack;

public class Classwork {
    public static void main(String[] args) {
        String[] test = new String[5];
        test[0] = "2 7 + 1 2 + +";
        test[1] = "1 2 3 4 + + +";
        test[2] = "3 3 + 7 * 9 2 / +";
        test[3] = "9 3 / 2 * 7 9 * + 4 -";
        test[4] = "5 5 + 2 * 4 / 9 +";
        for(int i = 0;i < test.length;i++) {
            System.out.println(test[i]+" = "+eval(test[i]));
        }
        

    }
    public static double eval(String postfix) {
        Stack<Double> s = new Stack<Double>();
        String[] chars = postfix.split(" ");
        double one = 0;
        double two = 0;
        for(String c : chars) {
            switch(c) {
                case "+":
                    two = s.pop();
                    one = s.pop();
                    s.push(one+two);
                    break;
                case "-":
                    two = s.pop();
                    one = s.pop();
                    s.push(one-two);
                    break;
                case "*":
                    two = s.pop();
                    one = s.pop();
                    s.push(one*two);
                    break;
                case "/":
                    two = s.pop();
                    one = s.pop();
                    s.push(one/two); 
                    break;
                default:
                    s.push(Double.parseDouble(c));
                    break;
            }
        }
        return s.pop();
    }
}