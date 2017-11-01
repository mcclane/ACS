public class Patient implements Comparable<Patient> {
    private String lastName, firstName, illness, note;
    private int priority, price, time;
    public Patient(String firstName, String lastName, String illness, int priority, int time) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.illness = illness;
        this.priority = priority;
        this.time = time;
    }
    public int getPriority() {
        return (1000*priority)+time;
    }
    public int compareTo(Patient p) {
        if(p.getPriority() > getPriority()) 
            return 1;
        else if(p.getPriority() < getPriority())
            return -1;
        return 0;
    }
}