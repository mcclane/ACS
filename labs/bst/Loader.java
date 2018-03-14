import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.NumberFormatException;


public class Loader {
    public static void save(BinaryTree<Account> accounts) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream("state.txt");
            out = new ObjectOutputStream(fos);
            out.writeObject(accounts);
            out.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }
    @SuppressWarnings("unchecked")
    public static BinaryTree<Account> load() {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        BinaryTree<Account> accounts = null;
        try {
            fis = new FileInputStream("state.txt");
            in = new ObjectInputStream(fis);
            accounts = (BinaryTree<Account>)(in.readObject());
            in.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        if(accounts == null) {
            // load from file instead
            accounts = new BinaryTree<Account>();
            try {
                Scanner scan = new Scanner(new File("names.txt"));
                int i = 1;
                while(scan.hasNext()) {
                    String[] splitted = scan.nextLine().split(",");
                    accounts.add(new Account(splitted[1], splitted[0], Math.random()*100000));
                }
            } 
            catch(FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return accounts;
    }
}
