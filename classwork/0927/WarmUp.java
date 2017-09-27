import java.util.Set;
import java.util.HashSet;

class Car {
    String name, make;
    int year;
    public Car(String name, String make, int year) {
        this.name = name;
        this.make = make;
        this.year = year;
    }
    public String toString() {
        return name+" "+make+" "+year;
    }
    public boolean equals(Object O) {
        Car c = (Car)(O);
        if(c.make.equals(make) && c.name.equals(name) && c.year == year) {
            return true;
        }
        return false;
    }
    public int hashCode()  {
        return 31*name.hashCode()*make.hashCode()*year;
    }
}
class Runner {
    public static void main(String[] args) {
        Set<Car> cars = new HashSet<Car>();
        cars.add(new Car("Focus", "Ford", 2005));
        cars.add(new Car("Accord", "Honda", 2014));
        cars.add(new Car("Camry", "Toyota", 2012));
        cars.add(new Car("Accord", "Honda", 2014));
        cars.add(new Car("Honda", "Accord", 2014));
        System.out.println(cars);
    }
    
} 