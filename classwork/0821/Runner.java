import java.util.Scanner;

public class Runner
{
	public static void main (String[] args)
  {
    Scanner input=new Scanner(System.in);
    Profile[] accs=new Profile[10];
    String[] names = {"Stephane Grosse", "Lourdes Demello", "Lyda Doke", "Jasper Shoaf", "Ilene Whipkey", "Philomena Krejci", "Tomoko Fearn", "Efren Mixer", "Brooke Corvin","Regina Wimbish"};
    for(int i = 0;i < accs.length;i++) {
      accs[i] = new Profile(names[i], (int)(Math.random()*99)+1);
    }
    
    while(true) {
      for(int i=0;i<accs.length;i++)
      {
        System.out.println(accs[i]);  
      }

      System.out.println("1. Sort Ascending by Age; 2. Scramble List; 3. Sort Ascending by Name");
      int user = input.nextInt();
      if(user==1)
      {
        for(int i=0;i<accs.length;i++)
        {
          for(int j=i+1;j<accs.length;j++)
          {
						if(accs[i].getAge()>accs[j].getAge())
            {
              Profile a=accs[j];
							accs[j]=accs[i];
              accs[i]=a;
              i=0;
            }
          }
        }
      }
      else if(user==2)
      {
        for(int i = 0;i < accs.length;i++) {
          int index = (int)(Math.random()*accs.length);
          Profile temp = accs[index];
          accs[index] = accs[i];
          accs[i] = temp;
        }
      }
      else if(user==3)
      {
        for(int i=0;i<accs.length;i++)
        {
          for(int j=i+1;j<accs.length;j++)
          {
						if(accs[i].getName().compareTo(accs[j].getName())>0)
            {
              Profile a=accs[j];
							accs[j]=accs[i];
              accs[i]=a;
              i=0;
            }
          }
        }
      }
      else
      {
				System.out.println("please enter a valid choice!");
      }
    }
  }
}