import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskStorage {
    public static final String FILE_PATH = "data/tasks.txt";
    
    public static ArrayList<Task> loadTasksFromFile(String filePath) {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs(); 
                file.createNewFile(); 
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|"); // Split by '|'
                String taskType = parts[0];
                String taskDescription = parts[1];
                Task task = new ToDo(taskDescription);
                switch (taskType) {
                    case "T":
                    task = new ToDo(taskDescription);
                    break;
                    case "D":
                    String by = parts[2];
                    task = new Deadline(taskDescription, by);
                    break;
                    case "E":
                    String from = parts[2];
                    String to = parts[3];
                    task = new Event(taskDescription, from, to);
                    break;
                }            
                loadedTasks.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
        
        return loadedTasks;
    }
    
    public static void writeTasksToFile(String filePath, ArrayList<Task> tasks) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Task task : tasks) {
                String dataString = task.toDataString(); 
                writer.write(dataString);
                writer.newLine(); 
            }
        }
    }
}

