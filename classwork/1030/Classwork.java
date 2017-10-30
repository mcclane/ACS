import java.util.Queue;
import java.util.Stack;
import java.util.Iterator;

public class Classwork{
  public static void main(String[] args)
  {
    String[] words = {"one two three two one",
      "1 2 3 4 5",
      "one two three four five",
      "a b c d e f g x y z g f h",
      "racecar is racecar",
      "1 2 3 a b c c b a 3 2 1",
      "chicken is a chicken"};
      
    for(int i=0;i<words.length;i++)
    {
      System.out.println(isPalindrome(words[i]));
    }
  }
  
  public static boolean isPalindrome(String input) {
        String[] words = input.split(" ");
        int length = words.length;
        Stack<String> s = new Stack<String>();
        for(int i = 0;i < length;i++) {
            if(i < length/2) {
                s.push(words[i]);
            }
            else if(length%2 == 0 && i >= length/2) {
                if(!s.pop().equals(words[i])) {
                    return false;
                }
            }
            else if(length%2 == 1 && i == length/2) {
                continue;
            }
            else if(length%2 == 1 && i > length/2) {
                if(!s.pop().equals(words[i])) {
                    return false;
                }
            }
        }
        return true;
    }
}