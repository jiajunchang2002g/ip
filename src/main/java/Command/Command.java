package Command;
import java.util.ArrayList;

import Storage.TaskStorage;
import Task.Task;
import Ui.Ui;

public abstract class Command {
    public abstract void execute(ArrayList<Task> tasks, Ui ui, TaskStorage storage) throws JarvisException;

    public boolean isExit() {
        return false;
    }
}
