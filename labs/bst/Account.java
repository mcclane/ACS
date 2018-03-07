public class Account implements Comparable<Account> {
    public String fname, lname;
    public double balance;
    public Account(String fname, String lname, double balance) {
        this.fname = fname;
        this.lname = lname;
        this.balance = balance;
    }
    public int compareTo(Account a) {
        if(lname.compareTo(a.lname) == 0)
            return fname.compareTo(a.fname);
        return lname.compareTo(a.lname);
    }
}