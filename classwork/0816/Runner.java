public class Runner {
	public static void main(String[] args) {
		Animal a1 = new Animal("dog", 5);
		System.out.println(a1);
		Manager.changeMe(a1, "cat", 3);
		System.out.println(a1);
	}
}