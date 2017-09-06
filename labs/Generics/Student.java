import java.awt.Graphics;

public class Student {
    private String name;
    private String imageFile;
    public Student(String name, String imageFile) {
        this.name = name;
        this.imageFile = imageFile;
    }
    public void drawStudent(Graphics g, int x, int y) {
        System.out.println("drawing student");
    }
    public void toString() {
        return name;
    }
}
