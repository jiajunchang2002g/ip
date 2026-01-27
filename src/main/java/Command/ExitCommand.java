package Command;
import java.util.ArrayList;

import Storage.TaskStorage;
import Task.Task;
import Ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, TaskStorage storage) {
        ui.showFarewell();
        ui.close();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
