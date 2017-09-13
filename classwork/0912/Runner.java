import java.util.ArrayList;
import java.util.Scanner;
import java.util.ListIterator;

class Item {
	private String name;
	private double price;
	public Item(String name, double price) {
		this.name = name;
		this.price = price;
	}
  public double getPrice() {
    return price;
  }
	public String toString() {
		return name+" "+price;
	}
}

public class Runner {
	public static void main(String[] args) {
		ArrayList<Item> al = new ArrayList<Item>();
		Scanner sc = new Scanner(System.in);
		while(true) {
			ListIterator<Item> lit=al.listIterator();
			System.out.println("Please enter 1 to add, 2 to delete, 3 to display, 4 to quit");
			int ip = sc.nextInt();
			if(ip == 1) {
				sc.nextLine();
				System.out.println("Enter item and price");
				String[] item_price = sc.nextLine().split(" ");
				boolean added = false;
				while(lit.hasNext()) {
					Item current = lit.next();
					if(Double.parseDouble(item_price[1]) <= current.getPrice()) {
						lit.previous();
						lit.add(new Item(item_price[0], Double.parseDouble(item_price[1])));
						added = true;
						break;
					}
				}
				if(added == false) {
					al.add(new Item(item_price[0], Double.parseDouble(item_price[1])));
				}
			}
			else if(ip == 2) {
				sc.nextLine();
				System.out.println("Enter item and price");
				String input = sc.nextLine();
				while(lit.hasNext())
				{
				  if(lit.next().toString().equals(input))
				  {
					lit.remove();
				  }
				}
			}
			else if(ip == 3) {
				double sum = 0;
				System.out.println(al.size());
				while(lit.hasNext()) {
				  Item curr = lit.next();
				  System.out.println(curr);
				  sum += curr.getPrice();
				}
				System.out.println("total: "+sum);
			}
			else if(ip == 4) {
				break;
			}
			else {
				System.out.println("Please enter something valid");
			}
			//al.add(new Animal(ip[0], Integer.parseInt(ip[1])));
		}
	}
}