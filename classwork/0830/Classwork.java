import java.util.ArrayList;

class Runner
{
  public static void main(String[] args)
  {
    ArrayList<Animal> a=new ArrayList<>();
    a.add(new Cat("McClane"));
    a.add(new Dog("Brian"));
    a.add(new Bird("Austin"));
    
    for(int i=0;i<a.size();i++)
    {
      a.get(i).printInfo();
    }
  }
}
  
abstract class Animal
{
  private String name;
  public Animal(String name) {
    this.name = name;
  }
  public abstract String speak();
  public abstract String getColor();
  public String getName() {
    return  "I am "+name;
  }
  public void printInfo() {
    System.out.println(getName()+" "+speak()+" "+getColor());
  }
}
  
class Cat extends Animal
{
  public Cat(String name)
  {
    super(name);
  }
  public String speak() {
    return "Meow";
  }
  public String getColor() {
    return "Black";
  }
}

class Dog extends Animal
{
  public Dog(String name)
  {
    super(name);
  }
  public String speak()
  {
    return "woof";
  }
  public String getColor() 
  {
    return "Brown";
  }
}

class Bird extends Animal
{
  public Bird(String name)
  {
    super(name);
  }
  public String speak() 
  {
    return "chrip";
  }
  public String getColor()
  {
    return "Blue";
  }
}