package Command;
import java.io.IOException;
import java.util.ArrayList;

import Storage.TaskStorage;
import Task.Task;
import Ui.Ui;

public class DeleteCommand extends Command {
    private final String arguments;

    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, TaskStorage storage) throws JarvisException {
        int index = parseIndex(arguments, "Please specify the task number to delete.");
        if (index >= tasks.size()) {
            throw new JarvisException("Invalid task number.");
        }
        Task removedTask = tasks.remove(index);
        ui.showMessage("Deleted task: " + removedTask);
        save(tasks, storage);
    }

    private int parseIndex(String arg, String emptyMessage) throws JarvisException {
        if (arg.isEmpty()) {
            throw new JarvisException(emptyMessage);
        }
        try {
            int index = Integer.parseInt(arg) - 1;
            if (index < 0) {
                throw new JarvisException("Invalid task number.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new JarvisException("Invalid task number.");
        }
    }

    private void save(ArrayList<Task> tasks, TaskStorage storage) throws JarvisException {
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            throw new JarvisException("Error saving tasks: " + e.getMessage());
        }
    }
}
