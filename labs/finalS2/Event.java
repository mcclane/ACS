import java.io.Serializable;


public class Event implements Serializable {
    public static final long serialVersionUID = 3L;
    String operation;
    String direction;
    int mouseX;
    int mouseY;
    int concerns;
    public Event(String operation, int concerns) {
        this.operation = operation;
        this.concerns = concerns;
    }
    public Event(String operation, int concerns, String direction) {
        this.operation = operation;
        this.concerns = concerns;
        this.direction = direction;
    }
    public Event(String operation, int concerns, int mouseX, int mouseY) {
        this.operation = operation;
        this.concerns = concerns;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }
    public String toString() {
        return operation+" "+concerns;
    }
}