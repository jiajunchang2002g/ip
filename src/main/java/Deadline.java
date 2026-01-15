public class Deadline extends Task {
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    // Java date time library
    public String by;

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
