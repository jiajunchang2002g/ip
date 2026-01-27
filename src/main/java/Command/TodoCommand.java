package Command;
import java.io.IOException;
import java.util.ArrayList;

import Storage.TaskStorage;
import Task.Task;
import Task.ToDo;
import Ui.Ui;

public class TodoCommand extends Command {
    private final String description;

    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, TaskStorage storage) throws JarvisException {
        if (description.isEmpty()) {
            throw new JarvisException("The description of a todo cannot be empty.");
        }
        Task task = new ToDo(description);
        tasks.add(task);
        ui.showMessage("Added ToDo: " + description);
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
