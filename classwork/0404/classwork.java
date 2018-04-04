import java.util.ArrayList;

class Task implements Runnable {
    private boolean done = false;
    public void run() {
        System.out.println("Starting "+Thread.currentThread().getName());
        try {
            Thread.sleep((int)(Math.random()*2+1)*1000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        done = true;
        System.out.println("Complete "+Thread.currentThread().getName());
    }
    public String toString() {
        return "Complete: "+done;
    }
}
class Manager {
    ArrayList<Task> tasks;
    ArrayList<Thread> threads;
    public Manager() {
        tasks = new ArrayList<Task>();
        threads = new ArrayList<Thread>();
        for(int i = 0;i < 50;i++) {
            tasks.add(new Task());
        }
    }
    public void start() {
        int progress = 0;
        for(int i = 0;i < 5;i++) {
            threads.add(new Thread(tasks.get(progress)));
            threads.get(i).start();
            progress++;
        }
        while(threads.size() > 0) {
            try {
                threads.get(0).join();
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
            threads.remove(0);
            if(progress < tasks.size()) {
                threads.add(new Thread(tasks.get(progress)));
                threads.get(threads.size()-1).start();
                progress++;
            }
        }
    }
    public void add(Task t) {
        tasks.add(t);
    }
    public String toString() {
        String out = "";
        for(Task t : tasks) {
            out += t.toString()+"\n";
        }
        return out;
    }
}
public class classwork {
    public static void main(String[] args) {
        Manager m = new Manager();
        System.out.println(m);
        m.start();
        System.out.println(m);
    }
}