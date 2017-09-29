public class Runner {
	public static void main(String[] args) {
		
		Exam1 ex1 = new Exam1();
		
		//20 Points
		//Add values to itemList using Generics
		ex1.addItemList("John",2001, 11);
		ex1.addItemList("Jen",2002, 12);
		ex1.addItemList("Jack",2003, 13);
		
		//10 Points
		//print itemList using iterators.
		System.out.println();
		ex1.printItemList();
		
		
		//20 Points
		//Add values to profileList using iterator sorted by the birth year.
		ex1.addProfileList("Henry",2003);
		ex1.addProfileList("Heather",2001);
		ex1.addProfileList("Jose",2004);
		ex1.addProfileList("Leslie",1999);
		
		//10 Points
		//Print profieList using iterators.
		System.out.println();
		ex1.printProfileList();
		
		//30 Points
		//Add values to hashList.  Make sure the Profile class is compatible
		//with HashSet when handling duplicates.
		ex1.addHashList("Cat", 2001);
		ex1.addHashList("Dog", 2002);
		ex1.addHashList("Dog", 2002);
		ex1.addHashList("Cat", 2003);
		
		//10 Points
		//Print hashList using iterators.
		System.out.println();
		ex1.printHashList();
	}
}
