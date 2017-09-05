public class Runner {
  public static void main(String[] args) {
    Item<Food> orange = new Item<Food>( new Food("orange", 20.00) );
    Item<Food> apple = new Item<Food>( new Food("apple", 10.00) );
    Item<String> str = new Item<String>( "123abc" );
    Item<String> str1 = new Item<String>( "abc123" );
    Item<Integer> num = new Item<Integer>((int)Math.random()*100);
		
    System.out.println(orange.get());
    System.out.println(apple.get());
    System.out.println(str.get());
    System.out.println(str1.get());
    System.out.println(num.get());
  }
}


class Food {
  private String name;
  private double price;
  public Food(String name, double price) {
    this.name = name;
    this.price = price;
  }
  public String toString() {
    return name+" "+price;
  }
}

class Item<T>
{
  private T o;
  
  public Item(T object)
  {
    o=object;
  }
  
  public T get()
  {
    return(o);
  }
}