import java.util.HashSet;

public class WarmUp {
	public static void main(String[] args) {
		String[] nums = "4 4 4 4 4 4 4 4".split(" ");
		HashSet<Integer> evens = new HashSet<Integer>();
		HashSet<Integer> odds = new HashSet<Integer>();
		for(int i = 0;i < nums.length;i++) {
			int n = Integer.parseInt(nums[i]);
			if(n%2 == 0) {
				evens.add(n);
			}
			else {
				odds.add(n);
			}
		}
		System.out.println("evens: "+evens);
		System.out.println("odds: "+odds);
	}
}