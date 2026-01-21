public class Deadline extends Task {
    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    public String by;

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + task + " | " + by;
    }
}
