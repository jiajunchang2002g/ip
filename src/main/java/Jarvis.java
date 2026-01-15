import java.util.ArrayList;
import java.util.Scanner;

public class Jarvis {

    public void greet() {
        System.out.println("Hello! I am Jarvis, your personal assistant.");
    }

    public void farewell() {
        System.out.println("Goodbye! Have a great day!");
    }

    public void run() {
        greet();

        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        while(true) {
            String input = scanner.nextLine();
            String command = input.split(" ")[0];
            String arguments = input.substring(command.length()).trim();
            switch(command) {
                case "mark":
                    int indexToMark = Integer.parseInt(arguments) - 1;
                        tasks.get(indexToMark).markAsDone();
                        System.out.println("Task marked as done.");
                    break;
                case "unmark":
                    int indexToUnmark = Integer.parseInt(arguments) - 1;
                        tasks.get(indexToUnmark).markAsNotDone();
                        System.out.println("Task marked as not done.");
                    break;
                case "list":
                    printTasks(tasks);
                    break;
                case "bye":
                    farewell();
                    scanner.close();
                    return;
                case "todo":
                    String todoDescription = arguments;
                    tasks.add(new ToDo(todoDescription));
                    System.out.println("Added ToDo: " + todoDescription);
                    break;
                case "deadline":
                    String[] deadlineParts = arguments.split(" /by ");
                    tasks.add(new Deadline(deadlineParts[0], deadlineParts[1]));
                    System.out.println("Added Deadline: " + deadlineParts[0] + " (by: " + deadlineParts[1] + ")");
                    break;
                case "event":
                    // E.g. event project meeting /from Mon 2pm /to 4pm
                    String[] eventParts = arguments.split(" /from | /to ");
                    tasks.add(new Event(eventParts[0], eventParts[1], eventParts[2]));
                    System.out.println("Added Event: " + eventParts[0] + " (from: " + eventParts[1] + " to: " + eventParts[2] + ")");
                    break;
            }
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
}
