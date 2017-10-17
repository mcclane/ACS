import java.util.Stack;

public class StackPushPop
{
	public static void main( String args[] )
	{
		//make a stack of integers named s
		Stack<Integer> s = new Stack<Integer>();
		//push 88, 23, and 11
		s.push(88);
        s.push(23);
        s.push(11);
		//print out the stack
		System.out.println(s);
		//pop 2 values
		s.pop();
        s.pop();
		//print out the stack
        System.out.println(s);
	}
}