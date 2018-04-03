import java.util.Scanner;

public class classwork {
    public static void main(String[] args) {
        
        // part 1
        Thread t1 = new Thread(new Count());
        Thread t2 = new Thread(new Count());
        Thread t3 = new Thread(new Count());
        t1.start();
        t2.start();
        t3.start();
        
        // part 2
        Thread cs = new Thread(new CountingStars());
        cs.start();
        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        cs.interrupt();
        
        // part 3
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Radius: ");
        int radius = sc.nextInt();
        System.out.println("Enter height: ");
        int height = sc.nextInt();
        AreaCircle ac = new AreaCircle(radius);
        Thread act = new Thread(ac);
        act.run();
        try {
            act.join();
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Area: "+ac.getArea());
        VolCylinder vc = new VolCylinder(ac.getArea(), height);
        Thread vct = new Thread(vc);
        vct.run();
        try {
            vct.join();
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Volume: "+vc.getVolume());
    }
}
class Count implements Runnable {
    public void run() {
        for(int i = 1;i < 11;i++) {
            System.out.println(""+i);
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class CountingStars implements Runnable {
    public void run() {
        int i = 1;
        while(true) {
            if(Thread.interrupted()) {
                System.out.println("I am not done");
                return;
            }
            i++;
        }
    }
}

class AreaCircle implements Runnable {
    int radius;
    double area;
    public AreaCircle(int radius) {
        this.radius = radius;
    }
    public void run() {
        area = Math.PI*(radius*radius);
        try {
            Thread.sleep(3000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public double getArea() {
        return area;
    }
}

class VolCylinder implements Runnable {
    int height;
    double area;
    double volume;
    public VolCylinder(double area, int height) {
        this.area = area;
        this.height = height;
    }
    public void run() {
        volume = area*height;
        try {
            Thread.sleep(5000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public double getVolume() {
        return volume;
    }
}