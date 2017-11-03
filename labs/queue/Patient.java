import java.awt.Graphics;

public class Patient implements Comparable<Patient> {
    String lastName, firstName, illness, note;
    int priority, price, time;
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
    public String toString() {
        return firstName+" "+lastName+" illness: "+illness+" Priority: "+ priority;
    }
}