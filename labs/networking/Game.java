import java.io.Serializable;
import java.awt.Graphics;
import java.awt.Color;

public class Game implements Serializable {
    String[][] grid;
    String status;
    String turn = "X";
    public Game() {
        grid = new String[3][3];
        for(int r = 0;r < grid.length;r++) {
            for(int c = 0;c < grid[r].length;c++) {
                grid[r][c] = " ";
            }
        }
        status = "Player 1 turn";
    }
    public void drawMe(Graphics g, int x, int y, int squareSize) {
        g.setColor(Color.black);
        g.drawString(status, x, y);
        for(int r = 0;r < grid.length;r++) {
            for(int c = 0;c <grid[r].length;c++) {
                g.drawRect(c*squareSize+x,r*squareSize+y, squareSize, squareSize);
                g.drawString(grid[r][c], c*squareSize+x+squareSize/2, r*squareSize+y+squareSize/2);
            }
        }
    }
    public boolean move(int r, int c, String letter) {
        System.out.println(turn);
        if(letter.equals(turn) && r < grid.length && c < grid[0].length && grid[r][c].equals(" ")) {
            grid[r][c] = letter;
            if(letter.equals("X")) {
                turn = "O";
            }
            else {
                turn = "X";
            }
            return true;
        }
        return false;
    }
    public boolean checkTicTacToe(String letter) {
        return false;
    }
}
