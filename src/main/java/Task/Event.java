package Task;
import java.time.LocalDateTime;

public class Event extends Task {
    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
        fromDateTime = LocalDateTime.parse(from);
        toDateTime = LocalDateTime.parse(to);
    }

    public Event(String task, String from, String to, boolean isDone) {
        super(task, isDone);
        this.from = from;
        this.to = to;
        fromDateTime = LocalDateTime.parse(from);
        toDateTime = LocalDateTime.parse(to);
    }

    public String from;
    public String to;
    public LocalDateTime fromDateTime;
    public LocalDateTime toDateTime;

    @Override 
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to " + to + ")";
    }

    @Override
    public String toDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + task + " | " + from + " | " + to;
    }
}
