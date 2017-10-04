import java.util.HashMap;
import java.util.TreeMap;
import java.util.Iterator;

class Car implements Comparable {
    String make, model;
    public Car(String ma, String mo) {
        make = ma;
        model = mo;
    }
    public String toString() {
        return make+", "+model;
    }
    public int compareTo(Object o) {
        Car c = (Car)(o);
        return model.compareTo(c.model)*100 + make.compareTo(c.make);
    }
}
public class warmup {
    public static void main(String[] args) {
        TreeMap<Car, Double> cars = new TreeMap<Car, Double>();
        String[] text = "Toyota, Corolla, $18,500;Toyota, Corolla LE, $18,935;Ford, Focus, $16,775;Ford, Focus, $16,800;Dodge, Dart, $16,995;Dodge, Dart, $16,995".split(";");
        for(String c : text) {
            String[] split = c.split(",");
            cars.put(new Car(split[0], split[1]), Double.parseDouble(split[2].trim().replace("$","").replace(",","")));
        }
        for(Car c : cars.keySet()) {
            System.out.println(c+" $"+cars.get(c));
        }
    }
}