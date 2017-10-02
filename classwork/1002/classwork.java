import java.util.HashMap;
import java.util.TreeMap;
import java.util.Iterator;

public class classwork {
    public static void main(String[] args) {
        HashMap<Integer, String> studentList = new HashMap<Integer, String>();
        studentList.put(123, "John");
        studentList.put(234, "Jack");
        studentList.put(234, "Jack");
        studentList.put(345, "Jim");
        studentList.put(456, "Jim");
        studentList.put(567, "Lewis");
        studentList.put(567, "Julian");
        for(int c : studentList.keySet()) {
            System.out.println(c +" "+studentList.get(c));
        }
        // part 2
        TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
        String s = "abcdefghiacdeghihk";
        for(char c : s.toCharArray()){
           if(map.containsKey(c)){
              map.put(c,map.get(c)+1);
           }
           else{
              map.put(c,1);
           }
        }
        for(char c : map.keySet()) {
            System.out.print(c +" ");
            for(int i = 0;i < map.get(c);i++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}