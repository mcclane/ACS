public class Schedule {

    private ArrayList<Pair<Integer, String>> mySchedule;
   
    public Schedule() {
        mySchedule = new ArrayList<Pair<Integer, String>>();
    }
    public void addClass(int period, String name) {
        boolean add = true;
        for(Pair p : mySchedule) {
            if(p.getObject1() == period) {
                add = false;
            }
        }
        if(add) {
            mySchedule.add(new Pair<Integer, String>(period, name));
        }
    }
    public String toString() {
        String str = "";
        for(Pair p : mySchedule) {
            str += p.getObject1()+" : "+p.getObject2()+"\n";
        }
        return str;
    }
}
