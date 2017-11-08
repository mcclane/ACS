public class Runner {
	public static void main(String[] args) {
		
		Exam2 ex2 = new Exam2();
		
		//50 Points - Stacks
		//(30 points setting up stack, pushing to stack, and popping to stack)
		//(20 points correct postfix calculation)
		double result1 = ex2.postFix("2 7 + 1 2 + +".split(" "));
		double result2 = ex2.postFix("1 2 3 4 + + +".split(" "));
		double result3 = ex2.postFix("3 3 + 7 * 9 2 / +".split(" "));
		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
		System.out.println();
		
		//20 Points - Adding to PriorityQueue of Profile
		ex2.addProfile("Jen", 34);
		ex2.addProfile("John", 50);
		ex2.addProfile("Carla", 30);
		ex2.addProfile("Jose", 10);
		ex2.addProfile("Maria", 28);
		
		
		//10 Points - Peeking the top
		ex2.peekTop();
		System.out.println();
		
		//10 Points - Removing the top
		ex2.removeTop();
System.out.println();

//10 Points - Print the PriorityQueue
		ex2.printProfiles();
		System.out.println();
	}
}
