import java.util.Scanner;
import java.util.ArrayList;
import java.util.ListIterator;

public class WarmUp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> al = new ArrayList<Integer>();
		ListIterator<Integer> it = al.listIterator();
		for(int i = 0;i < 5;i++) {
			it = al.listIterator();
			System.out.println("enter in number: ");
			int ip = sc.nextInt();
			boolean added = false;
			while(it.hasNext()) {
				if(it.next() > ip) {
					it.previous();
					it.add(ip);
					added = true;
					break;
				}
			}
			if(!added) {
				it.add(ip);
			}
		}
		it = al.listIterator();
		System.out.println();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}