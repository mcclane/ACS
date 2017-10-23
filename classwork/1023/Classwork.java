import java.util.Stack;
import java.util.Iterator;
import java.util.Scanner;

class CellPhone {
  private String number;
  private String id;
  private String date;
  public CellPhone(String number, String id) {
    this.number = number;
    this.id = id;
    date = null;
  }
  public void sell(String date) {
    this.date = date;
  }
  public String toString() {
    return number+" "+id+" Sold: "+date;
  }
}

public class Classwork  {
  public static void main(String[] args) {
    Iterator<CellPhone> it;
    Stack<CellPhone> inventory = new Stack<CellPhone>();
    Stack<CellPhone> sold = new Stack<CellPhone>();
    Scanner input = new Scanner(System.in);
    while(true) {
        System.out.println("1. Display Inventory\n2. Display Sold Phones\n3. Add Phone\n4. Sell Phone\n");
        int choice = input.nextInt();
        switch(choice) {
            case 1:
                printAll(inventory);
                break;
            case 2:
                printAll(sold);
                break;
            case 3:
                System.out.print("Input phone number: ");
                String num=input.next();
                System.out.print("Input phone id: ");
                String id=input.next();
                inventory.push(new CellPhone(num,id));
                break;
            case 4:
                System.out.println("Please enter the date");
                String date = input.next();
                CellPhone temp = inventory.pop();
                System.out.println("Sold phone: "+temp);
                temp.sell(date);
                sold.push(temp);
                break;
            default:
                System.out.println("Enter a valid choice, please");
                break;
        }
      }
    }
    
  public static void printAll(Stack<CellPhone> list)
  {
    Iterator<CellPhone> it;
    it=list.iterator();
    while(it.hasNext())
    {
      System.out.println(it.next().toString());
    }
    System.out.println();
  }
}