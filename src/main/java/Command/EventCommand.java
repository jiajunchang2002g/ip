package Command;
import java.io.IOException;
import java.util.ArrayList;

import Storage.TaskStorage;
import Task.Event;
import Task.Task;
import Ui.Ui;

public class EventCommand extends Command {
    private final String arguments;

    public EventCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, TaskStorage storage) throws JarvisException {
        if (arguments.isEmpty()) {
            throw new JarvisException("The description of an event cannot be empty.");
        }

        int fromIndex = arguments.indexOf(" /from ");
        int toIndex = arguments.indexOf(" /to ");
        if (fromIndex < 0 || toIndex < 0 || toIndex <= fromIndex) {
            throw new JarvisException("Please specify an event using '/from' and '/to'.");
        }

        String description = arguments.substring(0, fromIndex).trim();
        String from = arguments.substring(fromIndex + 7, toIndex).trim();
        String to = arguments.substring(toIndex + 5).trim();

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new JarvisException("Please specify an event using '/from' and '/to'.");
        }

        tasks.add(new Event(description, from, to));
        ui.showMessage("Added Event: " + description + " (from: " + from + " to: " + to + ")");
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
