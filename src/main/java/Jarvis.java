import java.util.ArrayList;
import java.util.Scanner;

public class Jarvis {

    private ArrayList<Task> tasks;
    private Scanner scanner;
    private String input;
    private String command;
    private String arguments;
    
    private enum CommandType {
        TODO("todo") {
            @Override
            public void handle(Jarvis jarvis, String arguments, ArrayList<Task> tasks) {
                jarvis.handleTodo(arguments, tasks);
            }
        }
        , DEADLINE("deadline") {
            @Override
            public void handle(Jarvis jarvis, String arguments, ArrayList<Task> tasks) {
                jarvis.handleDeadline(arguments, tasks);
            }
        }, EVENT("event") {
            @Override
            public void handle(Jarvis jarvis, String arguments, ArrayList<Task> tasks) {
                jarvis.handleEvent(arguments, tasks);
            }
        }, MARK("mark") {
            @Override
            public void handle(Jarvis jarvis, String arguments, ArrayList<Task> tasks) {
                jarvis.handleMark(arguments, tasks);
            }
        }, UNMARK("unmark") {
            @Override
            public void handle(Jarvis jarvis, String arguments, ArrayList<Task> tasks) {
                jarvis.handleUnmark(arguments, tasks);
            }
        }, LIST("list") {
            @Override
            public void handle(Jarvis jarvis, String arguments, ArrayList<Task> tasks) {
                jarvis.printTasks(tasks);
            }
        }, BYE("bye") {
            @Override
            public void handle(Jarvis jarvis, String arguments, ArrayList<Task> tasks) {
                jarvis.printFarewellMessage();
                jarvis.scanner.close();
                System.exit(0);
            }
        };
        
        private final String commandString;

        CommandType(String commandString) {
            this.commandString = commandString;
        }

        public abstract void handle(Jarvis jarvis, String arguments, ArrayList<Task> tasks);

        public static CommandType fromString(String commandString) {
            for (CommandType commandType : CommandType.values()) {
                if (commandType.commandString.equals(commandString)) {
                    return commandType;
                }
            }
            return null;
        }
    }

    public Jarvis() {
        this.tasks = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        printWelcomeMessage();
    }

    public void run() {
        while(true) {
            this.handleInput(scanner, tasks);
        }
    }

    public void printTasks(ArrayList<Task> tasks) {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
    }
    public static void main(String[] args) {
        Jarvis jarvis = new Jarvis();
        jarvis.run();
    }

    public void handleTodo(String arguments, ArrayList<Task> tasks) throws IllegalArgumentException {
        if (arguments.isEmpty()) {
            throw new IllegalArgumentException("The description of a todo cannot be empty.");
        }
        String description = arguments;
        tasks.add(new ToDo(description));
        System.out.println("Added ToDo: " + description);
    }

    public void handleEvent(String arguments, ArrayList<Task> tasks) throws IllegalArgumentException {
        if (arguments.isEmpty()) {
            throw new IllegalArgumentException("The description of an event cannot be empty.");
        }
        String[] parts = arguments.split(" /from | /to ");
        String description = parts[0];
        String from = parts[1];
        String to = parts[2];
        tasks.add(new Event(description, from, to));
        System.out.println("Added Event: " + description + " (from: " + from + " to: " + to + ")");
    }

    public void handleDeadline(String arguments, ArrayList<Task> tasks) throws IllegalArgumentException {
        if (arguments.isEmpty()) {
            throw new IllegalArgumentException("The description of a deadline cannot be empty.");
        }
        String[] parts = arguments.split(" /by ");
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

    public void handleMark(String arguments, ArrayList<Task> tasks) throws IllegalArgumentException {
        if (arguments.isEmpty()) {
            throw new IllegalArgumentException("Please specify the task number to mark.");
        }
        int index = Integer.parseInt(arguments) - 1;
        tasks.get(index).markAsDone();
        System.out.println("Task marked as done.");
    }

    public void handleUnmark(String arguments, ArrayList<Task> tasks) throws IllegalArgumentException {
        if (arguments.isEmpty()) {
            throw new IllegalArgumentException("Please specify the task number to unmark.");
        }
        int index = Integer.parseInt(arguments) - 1;
        tasks.get(index).markAsNotDone();
        System.out.println("Task marked as not done.");
    }

    public void handleInput(Scanner scanner, ArrayList<Task> tasks) {
        this.input = scanner.nextLine();
        this.command = input.split(" ")[0];
        this.arguments = input.substring(command.length()).trim();
        try {
            CommandType commandType = CommandType.fromString(this.command);
            if (commandType == null) {
                throw new IllegalArgumentException("Invalid command");
            }
            commandType.handle(this, this.arguments, tasks);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
