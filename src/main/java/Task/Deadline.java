package Task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    public Deadline(String task, String by) {
        super(task);
        this.by = by;
    }

    public Deadline(String task, String by, boolean isDone) {
        super(task, isDone);
        this.by = by;
        byDateTime = LocalDateTime.parse(by);
    }

    public String by;
    public LocalDateTime byDateTime;

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toDataString() {
        return "D | " + (isDone ? "1" : "0") + " | " + task + " | " + by;
    }
}
