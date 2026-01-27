package Command;
import java.io.IOException;
import java.util.ArrayList;

import Storage.TaskStorage;
import Task.Task;
import Ui.Ui;

public class MarkCommand extends Command {
    private final String arguments;

    public MarkCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, TaskStorage storage) throws DukeException {
        int index = parseIndex(arguments, "Please specify the task number to mark.");
        if (index >= tasks.size()) {
            throw new DukeException("Invalid task number.");
        }
        tasks.get(index).markAsDone();
        ui.showMessage("Task marked as done.");
        save(tasks, storage);
    }

    private int parseIndex(String arg, String emptyMessage) throws DukeException {
        if (arg.isEmpty()) {
            throw new DukeException(emptyMessage);
        }
        try {
            int index = Integer.parseInt(arg) - 1;
            if (index < 0) {
                throw new DukeException("Invalid task number.");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number.");
        }
    }

    private void save(ArrayList<Task> tasks, TaskStorage storage) throws DukeException {
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            throw new DukeException("Error saving tasks: " + e.getMessage());
        }
    }
}
