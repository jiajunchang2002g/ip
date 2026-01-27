import java.io.IOException;
import java.util.ArrayList;

import Command.Command;
import Command.JarvisException;
import Parser.Parser;
import Storage.TaskStorage;
import Task.Task;
import Ui.Ui;

public class Jarvis {

    private final ArrayList<Task> tasks;
    private final Ui ui;
    private final TaskStorage storage;

    public Jarvis() throws IOException {
        this.ui = new Ui();
        this.storage = new TaskStorage(TaskStorage.FILE_PATH);
        this.tasks = storage.loadTasks();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (JarvisException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Jarvis jarvis = new Jarvis();
        jarvis.run();
    }
}
