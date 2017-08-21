public class RandomArray {
	public static void main(String[] args) {
		int[] array = new int[10];
		for(int i = 0;i < 10;i++) {
			array[i] = (int)(Math.random()*99)+1;
		}
		for(int i = 0;i < 10;i++) {
			System.out.print(array[i]+" ");
		}
	}
}