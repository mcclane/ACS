public class Animal {
	private String name;
	private int age;
	public Animal(String name, int age) {
		this.name = name;
		this.age = age;
	}
	public String toString() {
		return name+" "+age;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}