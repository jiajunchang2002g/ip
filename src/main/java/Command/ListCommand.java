package Command;
import java.util.ArrayList;

import Storage.TaskStorage;
import Task.Task;
import Ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, TaskStorage storage) {
        ui.showTaskList(tasks);
    }
}
