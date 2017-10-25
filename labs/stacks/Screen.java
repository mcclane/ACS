import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.Stack;

public class Screen extends JPanel implements ActionListener, MouseListener {
        
    private Square[][] grid;
    private Stack<Square[][]> undoStack;
    private int x = 30;
    private int y = 30;
    private int gridSize = 800;
    private int squareSize = gridSize/16;
    
    private JButton undoButton;
    
    private Color color = new Color(0, 0, 0);
    
    private int paletteX = 1000;
    private int paletteY = 0;
    private boolean shouldDrawPalette = true;
    
    public Screen() {
        this.setLayout(null);
        addMouseListener(this);
        grid = new Square[16][16];
        for(int i = 0;i < grid.length;i++) {
            for(int j = 0;j < grid[i].length;j++) {
                grid[i][j] = new Square();
            }
        }
        undoStack = new Stack<Square[][]>();
        undoStack.push(copyArray(grid));
        
        undoButton = new JButton("Undo");
        undoButton.setBounds(x+gridSize, y, 100, 30);
        undoButton.addActionListener(this);
        this.add(undoButton);
        
    }
    public Dimension getPreferredSize() {
        // Sets the size of the panel
        return new Dimension(1200,1000);
    }
    public void paintComponent(Graphics g) {
		// draw background
        g.setColor(Color.black);
        //exclude the color palette so I don't have to redraw it each time.
        g.fillRect(0,0,1000,1000);
        if(shouldDrawPalette) {
            drawPalette(g);
            shouldDrawPalette = false;
        }
        Font font = new Font("Arial", Font.PLAIN, 16);
        g.setFont(font);
        g.setColor(Color.white);	
        drawGrid(g);
    }
    public void drawPalette(Graphics g) {
        for(int i = 0;i < 1000; i++) {
            for(int j = paletteX;j < 1200;j++) {
                g.setColor(new Color(i%255, j%255, i%255));
                g.fillRect(j, i, 1, 1);
            }
        }
    }
    public void drawGrid(Graphics g) {
        for(int i = 0;i < grid.length;i++) {
            for(int j = 0;j < grid[i].length;j++) {
                grid[i][j].drawMe(g, x+i*squareSize, y+j*squareSize, squareSize);
            }
        }
    }
    public Square[][] copyArray(Square[][] squares) {
        Square[][] copy = new Square[16][16];
        for(int i = 0;i < squares.length;i++) {
            for(int j = 0;j < squares[i].length;j++) {
                copy[i][j] = squares[i][j].copy();
            }
        }
        return copy;
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == undoButton) {
            if(undoStack.size() > 0) {
                grid = copyArray(undoStack.pop());
                repaint();
            }
            else {
                System.out.println("nothing left to undo");
            }
        }
    }
    public void mousePressed(MouseEvent e) {
        
    }
    public void mouseReleased(MouseEvent e) {
        
    }
    public void mouseEntered(MouseEvent e) {
        
    }
    public void mouseExited(MouseEvent e) {
        
    }
    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        if(mx <= x+gridSize && mx >= x && my >= y && my <= y+gridSize) {
            int xindex = (mx-x)/squareSize;
            int yindex = (my-y)/squareSize;
            if(xindex < 16 && xindex >= 0 && yindex < 16 && yindex >= 0) {
                grid[xindex][yindex].setColor(color);
                undoStack.push(copyArray(grid));
            }
            repaint();
        }
        else if(mx <= paletteX+200 && mx >= paletteX && my <= paletteY+1000 && my >= paletteY) {
            int red = my%255;
            int green = mx%255;
            int blue = my%255;
            color = new Color(red, green, blue);
        }
    }
}
