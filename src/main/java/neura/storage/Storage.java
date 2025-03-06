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

/**
 * Handles the saving and loading of tasks to/from a file. This class allows tasks to be
 * stored in a text file and later loaded back into the application.
 */
public class Storage {
    private static final String FILE_PATH = "./data/neura.txt";  // Path to save/load tasks
    private final String filePath;

    /**
     * Creates a new Storage object that will save/load tasks from the specified file path.
     * By default, it uses the static path "./data/neura.txt".
     */
    public Storage() {
        this.filePath = FILE_PATH;
    }

    /**
     * Parses a line from the file into a Task object.
     *
     * @param line A line from the file representing a task.
     * @return A Task object corresponding to the parsed line, or null if the line is invalid.
     */
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

    /**
     * Saves the list of tasks to a file.
     *
     * @param tasks The list of tasks to be saved.
     */
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

    /**
     * Converts a Task object to a string in the format required for saving to the file.
     *
     * @param task The Task object to be converted.
     * @return A string representation of the Task object in file format.
     */
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

    /**
     * Loads tasks from the file and adds them to the provided task list.
     *
     * @param tasks The list where loaded tasks will be added.
     */
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
