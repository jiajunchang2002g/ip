import java.util.*;
import java.io.*;

public class Jarvis {

    private ArrayList<Task> tasks;
    private Scanner scanner;
    private String input;
    private String command;
    private String arguments;
    
    private enum CommandType {
        TODO("todo") {
            @Override
            public void handle(Jarvis jarvis, String arguments) {
                jarvis.handleTodo(arguments);
            }
        }
        , DEADLINE("deadline") {
            @Override
            public void handle(Jarvis jarvis, String arguments) {
                jarvis.handleDeadline(arguments);
            }
        }, EVENT("event") {
            @Override
            public void handle(Jarvis jarvis, String arguments) {
                jarvis.handleEvent(arguments);
            }
        }, MARK("mark") {
            @Override
            public void handle(Jarvis jarvis, String arguments) {
                jarvis.handleMark(arguments);
            }
        }, UNMARK("unmark") {
            @Override
            public void handle(Jarvis jarvis, String arguments) {
                jarvis.handleUnmark(arguments);
            }
        }, LIST("list") {
            @Override
            public void handle(Jarvis jarvis, String arguments) {
                jarvis.printTasks();
            }
        }, BYE("bye") {
            @Override
            public void handle(Jarvis jarvis, String arguments) {
                jarvis.printFarewellMessage();
                jarvis.scanner.close();
                System.exit(0);
            }
        }, DELETE("delete") {
            @Override
            public void handle(Jarvis jarvis, String arguments) {
                if (arguments.isEmpty()) {
                    throw new IllegalArgumentException("Please specify the task number to delete.");
                }
                int index = Integer.parseInt(arguments) - 1;
                Task removedTask = jarvis.tasks.remove(index);
                System.out.println("Deleted task: " + removedTask.toString());
                try {
                    TaskStorage.writeTasksToFile(TaskStorage.FILE_PATH, jarvis.tasks);
                } catch (IOException e) {
                    System.out.println("Error saving tasks: " + e.getMessage());
                }
            }

        };
        
        private final String commandString;

        CommandType(String commandString) {
            this.commandString = commandString;
        }

        public abstract void handle(Jarvis jarvis, String arguments);

        public static CommandType fromString(String commandString) {
            for (CommandType commandType : CommandType.values()) {
                if (commandType.commandString.equals(commandString)) {
                    return commandType;
                }
            }
            return null;
        }
    }

    public Jarvis() throws IOException {
        this.scanner = new Scanner(System.in);        
        this.tasks = TaskStorage.loadTasksFromFile(TaskStorage.FILE_PATH);

        printWelcomeMessage();
    }

    public void run() {
        while(true) {
            this.handleInput(scanner);
        }
    }

    public void printTasks() {
        System.out.println("Here are your tasks:");
        for (Task task : this.tasks) {
            System.out.println(task);
        }
    }
    public static void main(String[] args) throws IOException {
        Jarvis jarvis = new Jarvis();
        jarvis.run();
    }

    public void handleTodo(String arguments) throws IllegalArgumentException {
        if (arguments.isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }
        String description = arguments;
        this.tasks.add(new ToDo(description));
        System.out.println("Added ToDo: " + description);
    }

    public void handleEvent(String arguments) throws IllegalArgumentException {
        if (arguments.isEmpty()) {
            throw new IllegalArgumentException("The description of an event cannot be empty.");
        }
        String[] parts = arguments.split(" /from | /to ");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Please specify an event using '/from' and '/to'.");
        }
        String description = parts[0];
        String from = parts[1];
        String to = parts[2];
        tasks.add(new Event(description, from, to));
        System.out.println("Added Event: " + description + " (from: " + from + " to: " + to + ")");
    }

    public void handleDeadline(String arguments) throws IllegalArgumentException {
        if (arguments.isEmpty()) {
            throw new IllegalArgumentException("The description of a deadline cannot be empty.");
        }
        String[] parts = arguments.split(" /by ");
        if (parts.length < 2) {
            throw new IllegalArgumentException("Please specify a deadline using '/by'.");
        }
        String description = parts[0];
        String by = parts[1];
        tasks.add(new Deadline(description, by));
        System.out.println("Added Deadline: " + description + " (by: " + by + ")");
    }

    public void printWelcomeMessage() {
        System.out.println("Welcome to Jarvis! How can I assist you today?");
    }

    public void printFarewellMessage() {
        System.out.println("Goodbye! Have a great day!");
    }

    public void handleMark(String arguments) throws IllegalArgumentException {
        if (arguments.isEmpty()) {
            throw new IllegalArgumentException("Please specify the task number to mark.");
        }
        int index = Integer.parseInt(arguments) - 1;
        tasks.get(index).markAsDone();
        System.out.println("Task marked as done.");
    }

    public void handleUnmark(String arguments) throws IllegalArgumentException {
        if (arguments.isEmpty()) {
            throw new IllegalArgumentException("Please specify the task number to unmark.");
        }
        int index = Integer.parseInt(arguments) - 1;
        tasks.get(index).markAsNotDone();
        System.out.println("Task marked as not done.");
    }

    public void handleInput(Scanner scanner) {
        this.input = scanner.nextLine();
        this.command = input.split(" ")[0];
        this.arguments = input.substring(command.length()).trim();

        try {
            CommandType commandType = CommandType.fromString(this.command);
            if (commandType == null) {
                throw new IllegalArgumentException("Invalid command");
            }
            commandType.handle(this, this.arguments);
            TaskStorage.writeTasksToFile(TaskStorage.FILE_PATH, this.tasks);
            
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
