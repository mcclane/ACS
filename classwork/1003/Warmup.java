import java.util.HashMap;
import java.util.TreeMap;
import java.util.Iterator;

public class Warmup {
    public static void main(String[] args) {
        TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
        String s = "1 2 3 4 5 6 1 2 3 4 5 1 3 1 2 3 4".replaceAll(" ", "");
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