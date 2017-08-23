public class TwoDArray {
	public static void main(String[] args) {
		//set it
		int[][] array = new int[5][3];
		for(int i = 0;i < array.length;i++) {
			for(int j = 0;j < array[i].length;j++) {
				array[i][j] = (int)(Math.random()*50+1);
			}
		}
		//print it
		for(int i = 0;i < array.length;i++) {
			for(int j = 0;j < array[i].length;j++) {
				System.out.print(array[i][j]+"\t");
			}
			System.out.println("\n");
		}
	}
}