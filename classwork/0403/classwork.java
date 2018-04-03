class Manager {
    private int[] numberList;
    private int index;
    public Manager() {
        numberList = new int[5];
        for(int i = 0;i < numberList.length;i++) {
            numberList[i] = (int)(Math.random()*10+1);
        }
        index = 0;
    }
    public synchronized void doubleIt() {
        System.out.println("Starting "+Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch(Exception e) {
            e.printStackTrace();
        }
        numberList[index] = numberList[index]*2;
        System.out.println("Completed "+Thread.currentThread().getName()+" : "+numberList[index]);
        index++;
    }
    public String toString() {
        String out = "";
        for(int i = 0;i < numberList.length;i++) {
            out += numberList[i]+"\n";
        }
        return out;
    }
}
class SimpleThread implements Runnable {
    private Manager manager;
    public SimpleThread(Manager manager){
        this.manager = manager;
    }
    public void run() {
        manager.doubleIt();
    }
}
public class classwork {
    public static void main(String[] args) {
        Manager m = new Manager();
        System.out.println(m);
        Thread[] threads = new Thread[5];
        for(int i = 0;i < threads.length;i++) {
            threads[i] = new Thread(new SimpleThread(m));
        }
        for(int i = 0;i < threads.length;i++) {
            threads[i].start();
        }
        for(int i = 0;i < threads.length;i++) {
            try {
                threads[i].join();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(m);
    }
}