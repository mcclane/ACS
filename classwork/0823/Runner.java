import java.util.Scanner;

public class Runner
{
  public static void main (String[] args)
  {
    GameManager gm = new GameManager();
    Scanner input = new Scanner(System.in);
    int turn = 1, p1Wins = 0, p2Wins = 0;
    
    while(true)
    {
   	 	gm.printGrid();
      turn*=-1;
      System.out.println();
      
      while(true)
      {
        if(turn==1)
        {
          System.out.print("Player 1 Row: ");
          int row = input.nextInt();
          System.out.print("Player 1 Column: ");
          int col = input.nextInt();
          if(gm.setSpace(col,row,"O"))
          {
            break;
          }
        }
        else if(turn==-1)
        {
          System.out.print("Player 2 Row: ");
          int row = input.nextInt();
          System.out.print("Player 2 Column: ");
          int col = input.nextInt();
          if(gm.setSpace(col,row,"X"))
          {
            break;
          }
        }
        

      }
        if(gm.checkTicTacToe().equals("O"))
        {
          gm.printGrid();
          System.out.println("Player 1 Wins!");
          p1Wins++;
          gm.clearGrid();
          System.out.println("Player 1 Wins: "+p1Wins);
          System.out.println("Player 2 Wins: "+p2Wins);
        }
        else if(gm.checkTicTacToe().equals("X"))
        {
          gm.printGrid();
          System.out.println("Player 2 Wins!");
          p2Wins++;
          gm.clearGrid();
          System.out.println("Player 1 Wins: "+p1Wins);
          System.out.println("Player 2 Wins: "+p2Wins);
        }
      else if(gm.checkTicTacToe().equals("Full"))
           {
             	gm.printGrid();
           		 System.out.println("Tie!");
             	gm.clearGrid();
             	System.out.println("Player 1 Wins: "+p1Wins);
          		System.out.println("Player 2 Wins: "+p2Wins);
          }
    }
  }
}