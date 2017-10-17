import java.util.Stack;
import java.util.HashMap;

public class Classwork {
    
    public static void main(String[] args) {
        HashMap<String,  String> pairs = new HashMap<String, String>();
        pairs.put("{","}");
        pairs.put("(",")");
        pairs.put("<",">");
        pairs.put("[","]");
        String[] test = new String[7];
        test[0] = "(abc(*def)";
        test[1] = "[{}]";
        test[2] = "[";
        test[3] = "[{<()>}]";
        test[4] = "{<html[value=4]*(12)>{$x}}";
        test[5] = "[one]<two>{three}(four)";
        test[6] = "car(cdr(a)(b)))";
        
        for(int i = 0;i < test.length;i++) {
            System.out.println(test[i]+": "+testBalance(test[i], pairs));
        }
    }
    public static boolean testBalance(String str, HashMap<String, String> pairs) {
        Stack<String> s = new Stack<String>();
        String[] split = str.split("");
        for(int i = 0;i < split.length;i++) {
            String c = split[i];
            if(pairs.containsKey(c)) {
                s.push(c);
            }
            else {
                for(String key : pairs.keySet()) {
                    if(c.equals(pairs.get(key))) {
                        if(s.size() == 0) {
                            return false;
                        }
                        String popped = s.pop();
                        if(popped.equals(key) == false) {
                            return false;
                        }
                    }
                } 
            }
            
        }
        if(s.size() == 0) {
            return true;
        }
        return false;
    }
}