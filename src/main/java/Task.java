public abstract class Task {
    private String task;
    private boolean isDone;

    public Task(String task) {
        this.task = task;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return (isDone ? "[X] " : "[ ] ") + task;
    }
}
