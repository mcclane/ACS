public class BankManager
{
	public void enableAccess(Account acc,int pin)
  {
  	acc.setAccess(pin);
  }
  
  public String toString(Account acc)
  {
    return(acc.getName()+", "+acc.getBalance());
  }
  
  public void deposit(Account acc, double amount)
  {
    acc.setBalance(acc.getBalance()+amount);
  }
  
  public void withdraw(Account acc, double x)
  {
    acc.setBalance(acc.getBalance()-x);
  }
}