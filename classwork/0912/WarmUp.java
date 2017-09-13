import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class WarmUp {
	public static void main(String[] args) {
		ArrayList<Animal> al = new ArrayList<Animal>();
		Scanner sc = new Scanner(System.in);
		for(int i = 0;i < 3;i++) {
			String[] ip = sc.nextLine().split(" ");
			al.add(new Animal(ip[0], Integer.parseInt(ip[1])));
		}
		Iterator it = al.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
class Animal {
	private String type;
	private int age;
	public Animal(String type, int age) {
		this.type = type;
		this.age = age;
	}
	public String toString() {
		return type+" "+age;
	}
}