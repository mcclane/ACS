import java.awt.Graphics;


public class Tree extends Thing {
    public Tree(double x, double y) {
        super("tree", x, y, 0, 0);
        this.lives = 5;
        this.height = 30;
        this.width = 30;
    }
    public void render(Graphics g) {
        g.setColor(Colors.TREE);
        g.drawRect((int)(x), (int)(y), width, height);
        g.fillOval((int)(x-9*width/6), (int)(y-9*height/6), width*4, height*4);
        g.setColor(Colors.DARK_TREE_WOOD);
        g.fillOval((int)x-5, (int)y-5, width+10, height+10);
        g.setColor(Colors.TREE_WOOD);
        g.fillOval((int)(x), (int)(y), width, height);
    }
    public void hit() {
        lives--;
        height--;
        width--;
    }
}