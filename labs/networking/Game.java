import java.io.Serializable;

public class Game implements Serializable {
    String[][] grid;
    public Game() {
        grid = new String[3][3];
        for(int r = 0;r < grid.length;r++) {
            for(int c = 0;c <grid[r].length;c++) {
                grid[r][c] = "";
            }
        }
    }
    public boolean move(int r, int c, String letter) {
        if(grid[r][c].equals(" ")) {
            grid[r][c] = letter;
            return true;
        }
        return false;
    }
    public boolean checkTicTacToe(String letter) {
        return false;
    }
}