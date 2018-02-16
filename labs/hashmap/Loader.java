import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Loader {
    public static void save(MyHashMap<Country, DLList<MyImage>> mhm) {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;
        try {
            fos = new FileOutputStream("state.txt");
            out = new ObjectOutputStream(fos);
            out.writeObject(mhm);
            out.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }
    @SuppressWarnings("unchecked")
    public static MyHashMap load() {
        FileInputStream fis = null;
        ObjectInputStream in = null;
        MyHashMap<Country, DLList<MyImage>> mhm = null;
        try {
            fis = new FileInputStream("state.txt");
            in = new ObjectInputStream(fis);
            mhm = (MyHashMap)(in.readObject());
            in.close();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return mhm;
    }
}