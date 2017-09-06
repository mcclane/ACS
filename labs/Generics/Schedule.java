import java.util.ArrayList;

public class Schedule {

    private ArrayList<Pair<Integer, String>> mySchedule;
   
    public Schedule() {
        mySchedule = new ArrayList<Pair<Integer, String>>();
    }

    public void addClass(int period, String name) {
        boolean add = true;
        for(Pair<Integer, String> p : mySchedule) {
            if(p.getKey() == period) {
                add = false;
            }
        }
        if(add) {
            mySchedule.add(new Pair<Integer, String>(period, name));
        }
    }
    public String toString() {
        String str = "";
        for(Pair<Integer, String> p : mySchedule) {
            str += p.getKey()+" : "+p.getValue()+"\n";
        }
        return str;
    }
}
