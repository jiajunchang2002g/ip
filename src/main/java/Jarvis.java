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
            switch(input) {
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
