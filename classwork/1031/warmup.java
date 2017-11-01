import java.util.Queue;
import java.util.LinkedList;

class Customer {
    private String name;
    private String phone;
    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    public String toString() {
        return name+" "+phone;
    }
}
public class warmup {
    public static void main(String[] args) {
        Queue<Customer> waitingList = new LinkedList<Customer>();
        waitingList.add(new Customer("Jack", "1234"));
        waitingList.add(new Customer("John", "4321"));
        waitingList.add(new Customer("Jake", "5678"));
        while(!waitingList.isEmpty()) {
            System.out.println(waitingList.poll());
        }
    }
}