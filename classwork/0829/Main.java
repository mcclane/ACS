import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Language[] l = new Language[4];
		l[0] = new Spanish();
		l[1] = new Chinese();
		l[2] = new French();
		l[3] = new Japanese();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("1. Spanish 2. Chinese 3. French 4. Japanese");
		int choice = sc.nextInt();
		if(choice <= 4 && choice >= 0) {
			System.out.println(l[choice-1].getLanguage());
			System.out.println(l[choice-1].getAuthor());
			System.out.println(l[choice-1].getHello());
			System.out.println(l[choice-1].getBye());
			System.out.println(l[choice-1].getThankYou());
		}
	}
}