package Command;
import java.io.IOException;
import java.util.ArrayList;

import Storage.TaskStorage;
import Task.Deadline;
import Task.Task;
import Ui.Ui;

public class DeadlineCommand extends Command {
    private final String arguments;

    public DeadlineCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, TaskStorage storage) throws JarvisException {
        if (arguments.isEmpty()) {
            throw new JarvisException("The description of a deadline cannot be empty.");
        }
        String[] parts = arguments.split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new JarvisException("Please specify a deadline using '/by'.");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        tasks.add(new Deadline(description, by));
        ui.showMessage("Added Deadline: " + description + " (by: " + by + ")");
        save(tasks, storage);
    }

    private void save(ArrayList<Task> tasks, TaskStorage storage) throws JarvisException {
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            throw new JarvisException("Error saving tasks: " + e.getMessage());
        }
    }
}
