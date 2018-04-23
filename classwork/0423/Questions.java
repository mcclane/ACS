import java.util.HashMap;

public class Questions {
    static HashMap<String, String> questions;
    public static void load() {
        questions = new HashMap<String, String>();
        questions.put("Where is Hatra?", "Iraq");
        questions.put("Where are the Cultural Landscape and Archaeological Remains of the Bamiyan Valley?",  "Afghanistan");
        questions.put("Where are the Minaret and Archaeological Remains of Jam?", "Afghanistan");
    }
    public static String getQuestion() {
        int choice = (int)(Math.random()*3);
        return (String)questions.keySet().toArray()[choice];
    }
    public static boolean check(String question, String answer) {
        return answer.equals(questions.get(question));
    }
}