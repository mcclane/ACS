public class Account {
	private String name;
  private double balance;
  private int pin;
  private boolean access;
  public Account(String n, double b, int p) {
  	name = n;
    balance = b;
    pin = p;
  }
  public String getName() {
    if(access) {
      return name;
    }
    return null;
  }
  public double getBalance() {
    if(access) {
      return balance;
    }
    return 0;
  }
  public boolean getAccess() {
    return access;
  }
  public void setAccess(int pin) {
    if(this.pin == pin) {
      access = true;
    }
    else {
      access = false;
    }
  }
  public void setBalance(double balance) {
    if(access) {
      this.balance = balance;
    }
  }
}