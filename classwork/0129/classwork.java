import java.util.LinkedList;
 
public class classwork{
    public static void main ( String[] args ){
         
        //What is the size of the hashtable?
        //This is a hashtable of what?
         
        @SuppressWarnings("unchecked") //generics are not compatible with arrays in Java
        LinkedList<Object>[] hashTable = new LinkedList[10];
         
         
        for(int i = 0; i< hashTable.length; i++){
            hashTable[i] = new LinkedList<Object>();
        }
        String[] data = "34 56 78 09 12 23 43 45 78 98 76 65 54 43 21 1 2 3 4 5 6 7 8 9 11 10 1 2 3 4".split(" ");
      for(String s : data) {
        int i = Integer.parseInt(s);
        if(!hashTable[i%10].contains(i)) {
       		hashTable[i%10].add(Integer.parseInt(s)); 
        }
      }
        //When printing the hashtable, what does the outer loop goes through?
        //What does the inner loop goes through?
        for(int i=0; i<hashTable.length; i++){
            System.out.print("Bucket "+i+": ");
             
            for(int j=0; j<hashTable[i].size(); j++){
                System.out.print( hashTable[i].get(j) + " ");
            }
             
            System.out.println();
        }          
    }
}