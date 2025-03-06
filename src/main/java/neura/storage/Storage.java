package neura.storage;

import neura.task.Deadline;
import neura.task.Event;
import neura.task.Task;
import neura.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private static final String FILE_PATH = "./data/neura.txt";  // Path to save/load tasks
    private final String filePath;

    public Storage() {
        this.filePath = FILE_PATH;
    }

    // Parse a line into a Task object
    public Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        try {
            switch (parts[0]) {
            case "T":
                return new Todo(parts[2]);
            case "D":
                return new Deadline(parts[2], parts[3]);
            case "E":
                return new Event(parts[2], parts[3], parts[4]);
            default:
                return null;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error parsing line: " + line);
            return null;
        }
    }

    // Save tasks to file
    public void saveTasks(ArrayList<Task> tasks) {
        File dataFile = new File(FILE_PATH);
        dataFile.getParentFile().mkdirs(); // Create directory if it doesn't exist
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
            for (Task task : tasks) {
                writer.write(taskToFileFormat(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    // Convert task to file format string
    public String taskToFileFormat(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + event.getFrom() + " | " + event.getTo();
        }
        return "";
    }

    // Load task to file
    public void loadTasks(ArrayList<Task> tasks) {
        File dataFile = new File(FILE_PATH);
        if (dataFile.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = parseTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading from file: " + e.getMessage());
            }
        }
    }
}
