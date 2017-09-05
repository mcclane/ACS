import java.util.ArrayList;

class Main {
	public static void main(String[] args) {
		ArrayList<MVHSStudentInterface> l = new  ArrayList<MVHSStudentInterface>();
		l.add(new LikesEnglish("Austin", "English", "More English", "Womens Studies"));
		l.add(new LikesMath("Brian Chao", "Calculus", "More calculus", "Less calculus"));
		for(MVHSStudentInterface s : l) {
			System.out.println(s);
		}
	}
}
interface MVHSStudentInterface {
	public String schoolName = "MVHS";
	public String getName();
	public String get1stPeriodSubject();
	public String get2ndPeriodSubject();
	public String get3rdPeriodSubject();
	public String toString();
}
class LikesEnglish implements MVHSStudentInterface {
	String name, one, two, three;
	public LikesEnglish(String name, String one, String two, String three) {
		this.name = name;
		this.one = one;
		this.two = two;
		this.three = three;
	}
	public String getName() {
		return name;
	}
	public String get1stPeriodSubject() {
		return one;
	}
		public String get2ndPeriodSubject() {
		return two;
	}
	public String get3rdPeriodSubject() {
		return three;
	}
	public String toString() {
		return "StudentName - "+name+"\nPeriod 1 - "+one+"\nPeriod 2 - "+two+"\nPeriod3 - "+three;
	}
}

class LikesMath implements MVHSStudentInterface {
	String name, one, two, three;
	public LikesMath(String name, String one, String two, String three) {
		this.name = name;
		this.one = one;
		this.two = two;
		this.three = three;
	}
	public String getName() {
		return name;
	}
	public String get1stPeriodSubject() {
		return one;
	}
		public String get2ndPeriodSubject() {
		return two;
	}
	public String get3rdPeriodSubject() {
		return three;
	}
	public String toString() {
		return "StudentName - "+name+"\nPeriod 1 - "+one+"\nPeriod 2 - "+two+"\nPeriod3 - "+three;
	}
}