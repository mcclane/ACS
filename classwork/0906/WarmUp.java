class MyItem<T> {
	private T it;
	public MyItem(T it) {
		this.it = it;
	}
	public T getIt() {
		return it;
	}
}
public class WarmUp {
	public static void main(String[] args) {
		MyItem<Integer> num = new MyItem<Integer>(10);
		MyItem<String> str = new MyItem<String>("10");
		MyItem<Double> db = new MyItem<Double>(10.0);
		System.out.println(num.getIt());
		System.out.println(str.getIt());
		System.out.println(db.getIt());

	}
}