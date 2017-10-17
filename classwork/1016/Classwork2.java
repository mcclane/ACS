import java.util.Stack;
import java.util.Scanner;

public class Classwork2
{
	public static void main( String args[] )
	{
		Stack<String> s = new Stack<String>();
        Scanner input = new Scanner(System.in);
        while(true) {
            System.out.println("1. View the entire stack\n2. View just the top of the stack.\n3. Add a word to a stack.\n4. Print the size of the stack.\n5. Remove an item from the top of the stack only if it is not empty.\n6. Clear the stack.\n7. Search for an item in the stack, and print its position.");
            int selection = input.nextInt();
            switch(selection) {
                case 1:
                    System.out.println(s);
                    break;
                case 2:
                    System.out.println(s.peek());
                    break;
                case 3:
                    System.out.println("Enter a word to add to the stack: ");
                    String newWord = input.next();
                    s.push(newWord);
                    break;
                case 4:
                    System.out.println("Stack size: "+s.size());
                    break;
                case 5:
                    if(s.size() > 0) {
                        System.out.println("Removed Item: "+s.pop());
                    }
                    else {
                        System.out.println("Stack already empty");
                    }
                    break;
                case 6: 
                    s = new Stack<String>();
                    break;
                case 7:
                    System.out.println("Enter a word to search: ");
                    String query = input.next();
                    System.out.println("Location: "+s.search(query));
                    break;
            }
        }
	}
}