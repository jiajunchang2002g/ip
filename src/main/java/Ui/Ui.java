package Ui;
import java.util.ArrayList;
import java.util.Scanner;

import Task.Task;

public class Ui {
    private static final String LINE = "================================";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Welcome to Jarvis! How can I assist you today?");
    }

    public void showFarewell() {
        System.out.println("Goodbye! Have a great day!");
    }

    public void showLine() {
        System.out.println(LINE);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void close() {
        scanner.close();
    }
}
