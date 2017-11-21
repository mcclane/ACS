import java.io.Serializable;
import java.awt.Graphics;
import java.awt.Color;
import java.net.URL;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Game implements Serializable {
    String[][] grid;
    String status;
    String turn = "X";
    int XScore = 0;
    int OScore = 0;
    boolean done = false;
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
        g.setColor(Color.green);
        g.fillRect(x+squareSize*3, y, squareSize, squareSize);
        g.setColor(Color.black);
        g.drawString("You can only reset if it is your turn!", 500, 50);
        g.drawString("Reset", x+squareSize*3, y+squareSize/2);
        g.drawString(status, x, y);
        g.drawString("X: "+XScore+" O Score: "+OScore, x+100, y);
        for(int r = 0;r < grid.length;r++) {
            for(int c = 0;c <grid[r].length;c++) {
                g.drawRect(c*squareSize+x,r*squareSize+y, squareSize, squareSize);
                g.drawString(grid[r][c], c*squareSize+x+squareSize/2, r*squareSize+y+squareSize/2);
            }
        }
    }
    public boolean move(int r, int c, String letter) {
        if(r == 0 && c == 3 && turn.equals(letter)) {
            reset();
        }
        if(!done && letter.equals(turn) && r < grid.length && c < grid[0].length && grid[r][c].equals(" ")) {
            grid[r][c] = letter;
            if(letter.equals("X")) {
                turn = "O";
                status = "O's turn";
                playSound("sound/cannon.wav");
            }
            else {
                turn = "X";
                status = "X's turn";
            }
            if(checkTicTacToe("X")) {
                done = true;
                XScore++;
                status = "x wins, "+status;
            }
            else if(checkTicTacToe("O")) {
                done = true;
                OScore++;
                status = "o wins";
            }
            else if(checkTie()) {
                done = true;
                status = "Tie";
            }
            System.out.println("X: "+checkTicTacToe("X"));
            System.out.println("O: "+checkTicTacToe("O"));
            return true;
        }
        return false;
    }
    public boolean checkTie() {
        for(int i = 0;i < grid.length;i++) {
            for(int j = 0;j < grid[i].length;j++) {
                if(grid[i][j].equals(" ")) {
                    return false;
                }
            }
        }
        return true;
    }
    public boolean checkTicTacToe(String letter) {
        for(int i = 0;i < grid.length;i++) {
            if(grid[i][0].equals(letter) && grid[i][1].equals(letter) && grid[i][2].equals(letter)) {
                return true;
            }
            else if(grid[0][i].equals(letter) && grid[1][i].equals(letter) && grid[2][i].equals(letter)) {
                return true;
            }
        }
        if(grid[0][0].equals(letter) && grid[1][1].equals(letter) && grid[2][2].equals(letter)) {
            return true;
        }
        if(grid[0][2].equals(letter) && grid[1][1].equals(letter) && grid[2][0].equals(letter)) {
            return true;
        }
        return false;
    }
    public void reset() {
        done = false;
        for(int i = 0;i < grid.length;i++) {
            for(int j = 0;j < grid[i].length;j++) {
                grid[i][j] = " ";
            }
        }
        status = turn+"'s turn";
    }
    public void playSound(String filename) {
        try {
            URL url = this.getClass().getClassLoader().getResource(filename);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(url));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
}
