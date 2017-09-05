public class Runner {
  public static void main(String[] args) {
    People[] people = new People[12];
    people[0] = new People("john", 21);
    people[1] = new People("john1", 22);
    people[2] = new People("john2", 23);
    people[3] = new Employee("sally", 42, 2);    
    people[4] = new Employee("sally1", 42, 6);
    people[5] = new Employee("sally2", 42, 18);
	people[6] = new Student("Austin", 17, "MVHS");
	people[7] = new Student("Austin1", 18, "MVH");
	people[8] = new Student("Austin2", 19, "MV");
    people[9] = new CollegeStudent("Josh", 24, "Chico State", "Gender Studies");
    people[10] = new CollegeStudent("Josh1", 39, "Rice", "Race Studies");
    people[11] = new CollegeStudent("Brian Chao Chao", 44, "Foothill", "Gender is on a Spectrum");
                            
    for(int i=0;i<people.length;i++)
    {
    	System.out.println(people[i].toString());
    }
  }
}