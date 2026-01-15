import java.util.Scanner;

public class Jarvis {
    public static void main(String[] args) {
        System.out.println("Hello, I am Jarvis.");

        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.print("You: ");
            input = scanner.nextLine();
            System.out.println("Jarvis: " + input);
        } while (!input.equalsIgnoreCase("bye"));

        System.out.println("Bye!");
        scanner.close();
    }
}
