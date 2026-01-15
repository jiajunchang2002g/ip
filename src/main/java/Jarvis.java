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
            switch(command) {
                case "mark":
                    int indexToMark = Integer.parseInt(input.split(" ")[1]) - 1;
                        tasks.get(indexToMark).markAsDone();
                        System.out.println("Task marked as done.");
                    break;
                case "unmark":
                    int indexToUnmark = Integer.parseInt(input.split(" ")[1]) - 1;
                        tasks.get(indexToUnmark).markAsNotDone();
                        System.out.println("Task marked as not done.");
                case "list":
                    printTasks(tasks);
                    break;
                case "bye":
                    farewell();
                    scanner.close();
                    return;
                default:
                    Task task = new Task(input);
                    tasks.add(task);
                    System.out.println("Task added.");
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
