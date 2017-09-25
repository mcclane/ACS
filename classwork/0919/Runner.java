import java.util.Scanner;
import java.util.Arrays;

public class Runner
{
	public static void main(String[] args)
  {
    System.out.println("Size of integer array: ");
	Scanner input = new Scanner(System.in);
    int size=input.nextInt();
    int[] nums = new int[size];
	for(int i = 0;i < size;i++) {
		nums[i] = i;
	}
    Search s = new Search();
    while(true) {
		System.out.println(Arrays.toString(nums));
      System.out.println("Enter a number");
      int value = input.nextInt();
      s.passes = 0;
      int pos = s.binarySearch(nums, value, 0, nums.length-1);
      System.out.println("Position: "+pos+" Passes: "+s.passes);
    }
  }
}

class Search {
  static int passes = 0;
  public Search() {}
  public int binarySearch(int[] list, int value, int start_pos, int end_pos) {
	if(start_pos <= end_pos) {
		int mid_pos= (start_pos + end_pos)/2;
		passes++;
		if(list[mid_pos] == value) {
		  System.out.println("found");
		  return mid_pos;
		}
		else if(list[mid_pos] > value) {
		  //search bottom half
		  System.out.println("split");
		  return binarySearch(list, value, start_pos, mid_pos-1);
		}
		else if(list[mid_pos] < value) {
		  //search upper half
		  System.out.println("split");
		  return binarySearch(list, value, mid_pos+1, end_pos);
		}
	}
	return -1;
  }
}