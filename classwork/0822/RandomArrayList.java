import java.util.ArrayList;

public class RandomArrayList {
	public static void main(String[] args) {
		ArrayList<Integer> array = new ArrayList<>();
		for(int i = 0;i < 10;i++) {
			array.add((int)(Math.random()*999)+1);
		}
		System.out.print(array);
	}
}

