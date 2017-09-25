import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

public class Runner
{
	public static void main(String[] args)
  {
  	String[] letters = "a b c d e f g h a b c d e f g h i j k".split(" ");
    Set<String> uniques = new HashSet<String>();
    Set<String> duplicates = new HashSet<String>();
		for(int i = 0;i < letters.length;i++) {
			if(uniques.add(letters[i]) == false) {
				duplicates.add(letters[i]);
			}
		}
		System.out.println(uniques);
		System.out.println(duplicates);
    
    letters = "one two three one two three six seven one two".split(" ");
    uniques = new HashSet<String>();
    duplicates = new HashSet<String>();
		for(int i = 0;i < letters.length;i++) {
			if(uniques.add(letters[i]) == false) {
				duplicates.add(letters[i]);
			}
		}
		System.out.println("Uniques: "+uniques);
		System.out.println("Duplicates: "+duplicates);
    letters = "3 5 4 7 5 17 29 17 4 6 56 72 6".split(" ");
    Set<Integer> evens = new HashSet<Integer>();
    Set<Integer> odds = new HashSet<Integer>();
		for(int i = 0;i < letters.length;i++) {
      int curr = Integer.parseInt(letters[i]);
			if(curr%2==0 && evens.add(curr)) {}
      else if(curr%2==1 && odds.add(curr)) {}
		}
		System.out.println("odds: "+odds);
		System.out.println("evens: "+evens);
    String[] listOne="1 5 9 4 6 8 12 1".split(" ");
    String[] listTwo="6 5 8 9 11 7 10 11".split(" ");
    uniques = new HashSet<String>();
    duplicates = new HashSet<String>();
		for(int i = 0;i < listOne.length;i++) {
			uniques.add(listOne[i]);
		}
    for(int i = 0;i < listTwo.length;i++) {
      if(uniques.contains(listTwo[i])) {
				duplicates.add(listTwo[i]);
			}
    }
    System.out.println("Duplicates: "+duplicates);
  }
}