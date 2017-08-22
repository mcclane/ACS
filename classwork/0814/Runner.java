import java.util.Scanner;

public class Runner {
  public static void main(String[] args) {
    Account acc1 = new Account("user1", 300, 1234);
    Account acc2 = new Account("user2", 200, 4321);
    Account acc = new Account("blank",000,0000);
	
    BankManager bm = new BankManager();
    Scanner s = new Scanner(System.in);
    while(true) {
      System.out.println("please enter username");
      String uname = s.next();
      if(uname.equals("user1")) {
        acc = acc1;
      }
      else if(uname.equals("user2")) {
        acc = acc2;
      }
      else {
        System.out.println("enter a valid user");
        continue;
      }
      System.out.println("please enter pin");
      int pin = s.nextInt();
      bm.enableAccess(acc, pin);
     
      while(acc.getAccess()) {
		System.out.println(bm.toString(acc));
        System.out.println("1. deposit 2. withdraw 3. logout");
        int choice = s.nextInt();
        if(choice==1)
        {
          System.out.println("Desposit Amount: ");
          double amount=s.nextDouble();
          bm.deposit(acc,amount);
        }
        else if(choice==2)
        {
					System.out.println("Withdrawal amout");
          double amt  = s.nextDouble();
          bm.withdraw(acc, amt);
        }
        else if(choice==3) 
        {
          System.out.println("please enter pin");
          pin = s.nextInt();
          bm.enableAccess(acc, pin);
          if(acc.getAccess()) {
			  System.out.println("logged out");
            break;
          }
        }
        else
        {
          System.out.println("Please choose a valid choice"); 
        }
      }
    }
  }
}