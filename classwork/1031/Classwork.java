import java.util.Queue;
import java.util.PriorityQueue;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Iterator;

public class Classwork{
  public static void main(String[] args)
  {
    Queue<Ticket> completedTickets=new LinkedList<Ticket>();
    PriorityQueue<Ticket> serviceTickets=new PriorityQueue<Ticket>();
    Scanner input=new Scanner(System.in);
    
    while(true)
    {
      System.out.println("1. Create Ticket\n2. Service Ticket\n3. View Completed");
      int choice=input.nextInt();
      input.nextLine();
      switch(choice)
      {
        case 1:
          System.out.print("Input name: ");
          String name=input.nextLine();
          System.out.print("Input problem: ");
          String prob=input.nextLine();
          System.out.print("Input priority (high/low): ");
          String priority=input.nextLine();
          serviceTickets.add(new Ticket(name,prob,priority));
          break;
        case 2:
          Ticket temp=serviceTickets.poll();
          System.out.println(temp.toString());
          System.out.print("Input service note: ");
          String note=input.nextLine();
          //input.nextLine();
          temp.setServiceNote(note);
          completedTickets.add(temp);
          break;
        case 3:
          Iterator<Ticket> it=completedTickets.iterator();
          while(it.hasNext())
          {
            System.out.println(it.next().toString());
          }
          break;
        default:
          System.out.println("Invalid input");
          break;
      }
      System.out.println();
    }
  }
}

class Ticket implements Comparable<Ticket>{
	private String name,prob,serviceNote="";
	private int priority;
	
	public Ticket(String name,String prob,String priorityWord)
	{
	  this.name=name;
	  this.prob=prob;
	  priority=0;
	  if(priorityWord.equalsIgnoreCase("high"))
	  {
	    priority=1;
	  }
	}
	
	public void setServiceNote(String note)
	{
	  serviceNote=note;
	}
	
	public int getNumber(){
		return priority;
  }
	
	@Override
  public int compareTo(Ticket other){
  	if( priority > other.getNumber() ){
  		return -1; //If the current number is less than the other
  	}else if( priority < other.getNumber() ){
  		return 1; //If the current number is greater than the other
  	} else {
  		return 0; //If they are the same
  	}
  }
  
  public String toString()
  {
    return(name+"\n"+prob+"\n"+serviceNote);
  }
}
