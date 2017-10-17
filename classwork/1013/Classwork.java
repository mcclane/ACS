import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.TreeSet;
import java.util.TreeMap;

public class Classwork {
  public static void main(String[] args) {
    //arraylist
    String data = "3 5 7 3 5 72 17 4 29 4 6 56 4 72 17 3";
    ArrayList<Integer> evens = new ArrayList<Integer>();
    ArrayList<Integer> odds = new ArrayList<Integer>();
    for(String s : data.split(" ")) {
      int n = Integer.parseInt(s);
      if(evens.contains(n) || odds.contains(n)) { //check for duplicates
        continue;
      }
      if(n%2 == 0) {
        evens.add(n);
      }
      else {
        odds.add(n);
      }
    }
  	evens=sort(evens);
    odds=sort(odds);
    System.out.println("ODDS:\t"+odds);
    System.out.println("EVENS:\t"+evens);
    
    //set
    Set<Integer> evenSet = new TreeSet<Integer>();
    Set<Integer> oddSet = new TreeSet<Integer>();
    for(String s : data.split(" ")) {
      int n = Integer.parseInt(s);
      if(n%2 == 0) {
        evenSet.add(n);
      }
      else {
        oddSet.add(n);
      }
    }
    System.out.println("ODDS:\t"+oddSet);
    System.out.println("EVENS:\t"+evenSet);
    
    data = "Water Pump,19934,Ford,Taurus,1999\nAir    Filter,98765,Chevy,Silverado,2002\nFuel Filter,19967,Ford,Taurus,1999\nRadiator,23102,Dodge,Dakota,2001\nFuel Filter,19967,Ford,Taurus,1999\nWiper Blades,12321,Chevy,Camaro,2002\nWater Pump,19912,Ford,Expedition,1997\nRadiator,23102,Dodge,Dakota,2001\nWater Pump,19934,Ford,Taurus,1999\nAir Filter,98765,Chevy,Silverado,2002\nWiper Blades,12321,Chevy,Camaro,2002";
    //map
 	TreeMap<String, Integer> PartList = new TreeMap<String, Integer>();
    for(String line : data.split("\n")) {
      if(PartList.containsKey(line)) {
        PartList.put(line, PartList.get(line)+1);
      }
      else {
        PartList.put(line, 1);
      }
    }
    //print out the PartList
    for(String k : PartList.keySet()) {
      String[] splitted = k.split(",");
      System.out.println(splitted[2]+" "+splitted[3]+" "+splitted[4]+" - "+splitted[0]+" "+splitted[1]+" "+splitted[2]+" - "+PartList.get(k));
    }
  }
  
  public static ArrayList<Integer> sort(ArrayList<Integer> list)
  {
    for(int i=0;i<list.size();i++)
    {
      for(int j=0;j<list.size();j++)
      {
        if(list.get(i)<list.get(j))
        {
          int temp=list.get(i);
          list.set(i,list.get(j));
          list.set(j,temp);
          
        }
      }
    }
    return(list);
  }
}