public class Deadline extends Task {
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String by;

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
