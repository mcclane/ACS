import java.util.ArrayList;
import java.util.Scanner;

public class Runner
{
	public static void main(String[] args)
  {
   	Scanner input = new Scanner(System.in);
    ArrayList<Task> tasks = new ArrayList<Task>();
    
    while(true)
    {
      System.out.print("1. Add a task; 2. Delete a task; 3. Exit");
	  int selection = input.nextInt();
      if(selection == 1) {
        String task = input.next();
        int rank = input.nextInt();
        if(rank < 1) {
          System.out.println("number too small");
        }
        else {
        	tasks.add(new Task(task, rank));
        }
      }
      else if(selection == 2) {
        System.out.print("Input the task name: ");
        String task = input.next();

        for(int i = 0;i < tasks.size();i++) {
          if(tasks.get(i).getTask().equals(task)) {
            tasks.remove(i);
            i--;
		  }
		}
	  }
      else if(selection == 3) {
        System.out.println("goodbye");
        break;
      }
      else
      {
        System.out.println("please enter valid choice");    
      }
      sort(tasks);
      for(int i = 0;i < tasks.size();i++)  {
      	System.out.println(tasks.get(i));
      }
    }
  }
    
  public static void sort(ArrayList<Task> list)
  {
    for(int i=0;i<list.size();i++)
    {
      for(int j=i+1;j<list.size();j++)
      {
        if(list.get(i).getRank()>list.get(j).getRank())
        {
          Task a=list.get(j);
          list.set(j,list.get(i));
          list.set(i,a);
          i=0;
        }
  	}
  }
  }
}