public class Account {
	private String name;
	private double balance;
	private int pin;
	private boolean access;
	public Account(String name, double balance, int pin) {
		this.name = name;
		this.balance = balance;
		this.pin = pin;
		access = false;
	}
	public String getName() {
		return name;
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
	public void withdraw(double amount) {
		if(access) {
			balance -= amount;
		}
	}
	public void deposit(double amount) {
		if(access) {
			balance += amount;
		}
	}
}