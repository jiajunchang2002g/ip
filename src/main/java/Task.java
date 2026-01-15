public class Task {
    /* 
    ToDos: tasks without any date/time attached to it e.g., visit new theme park
    Deadlines: tasks that need to be done before a specific date/time e.g., submit report by 11/10/2019 5pm
    Events: tasks that start at a specific date/time and ends at a specific date/time
    
    e.g., (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019 
    */
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
