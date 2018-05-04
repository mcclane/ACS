import java.io.Serializable;


public class Event implements Serializable {
    public static final long serialVersionUID = 3L;
    String operation;
    String direction;
    int mouseX;
    int mouseY;
    double dx;
    double dy;
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
    // a shoot event
    public Event(String operation, int concerns, double dx, double dy) {
        this.operation = operation;
        this.concerns = concerns;
        this.dx = dx;
        this.dy = dy;
    }
    public String toString() {
        return operation+" "+concerns;
    }
}