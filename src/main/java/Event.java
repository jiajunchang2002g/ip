public class Event extends Task {
    public Event(String task, String from, String to) {
        super(task);
        this.from = from;
        this.to = to;
    }

    public String from;
    public String to;

    @Override 
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to " + to + ")";
    }

    @Override
    public String toDataString() {
        return "E | " + (isDone ? "1" : "0") + " | " + task + " | " + from + " | " + to;
    }
}
