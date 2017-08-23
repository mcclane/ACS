//https://codeshare.io/5Rn4vL
public class GameManager {
  
  private String[][] grid;
  
  public GameManager() {
    grid = new String[3][3];
	for(int i = 0;i < grid.length;i++) {
		for(int j = 0;j < grid[i].length;j++) {
			grid[i][j] = " ";
		}
	}
  }
  
  public boolean setSpace(int r, int c, String ox) {
    r--;
    c--;
    
    if(r>3 || r<0 || c>3 || c<0)
    {
      return false;
    }
    if(grid[r][c].equals(" ")) {
      grid[r][c] = ox;
      return true;
    }
    return false;
  }
  public void printGrid() {
		System.out.println("-------------------------------------------------");
    for(int i=0; i<grid.length;i++)
    {
      System.out.print("|\t");
      for(int j=0;j<grid[i].length;j++)
      {
        System.out.print(grid[i][j]+"\t|\t");
      }
      System.out.println();
      System.out.println("-------------------------------------------------");
    }
  }
  
  public void clearGrid() {
	  grid = new String[3][3];
	  for(int i = 0;i < grid.length;i++) {
		for(int j = 0;j < grid[i].length;j++) {
			grid[i][j] = " ";
		}
	}
  }
  public String checkTicTacToe() {
    if(checkTicTacToe("X")) {
      return "X";
    }
    else if(checkTicTacToe("O")) {
      return "O";
    }
    boolean full = true;
    for(int i = 0;i < grid.length;i++) {
      for(int j = 0;j < grid[i].length;j++) {
        if(grid[i][j].equals(" ")){
          full = false;
        }
      }
    }
    if(full) {
      return "Full";
    }
    return " ";
  }
            
  public boolean checkTicTacToe(String xo) {
    if(grid[0][0].equals(xo) && grid[1][1].equals(xo) && grid[2][2].equals(xo)) {
      return true;
    }
    else if(grid[2][0].equals(xo) && grid[1][1].equals(xo) && grid[0][2].equals(xo)) {
      return true;
    }
    else if(grid[0][0].equals(xo) && grid[0][1].equals(xo) && grid[0][2].equals(xo)) {
      return true;
    }
    else if(grid[1][0].equals(xo) && grid[1][1].equals(xo) && grid[1][2].equals(xo)) {
      return true;
    }
    else if(grid[2][0].equals(xo) && grid[2][1].equals(xo) && grid[2][2].equals(xo)) {
      return true;
    }
    else if(grid[0][0].equals(xo) && grid[1][0].equals(xo) && grid[2][0].equals(xo)) {
      return true;
    }
    else if(grid[0][1].equals(xo) && grid[1][1].equals(xo) && grid[2][1].equals(xo)) {
      return true;
    }
    else if(grid[0][2].equals(xo) && grid[1][2].equals(xo) && grid[2][2].equals(xo)) {
      return true;
    }
    else {
      return false;
    }
  }
}