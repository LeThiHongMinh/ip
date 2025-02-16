package neura.ui;

import neura.exception.NeuraException;
import neura.task.Deadline;
import neura.task.Event;
import neura.task.Task;
import neura.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Neura {

    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String DELETE_COMMAND = "delete";
    private static final String TASK_LIST_FULL_MESSAGE = "Task list is full. Cannot add more tasks.";
    private static final String SEPARATOR = "____________________________________________________________";  // Constant for separator line
    private static final String FILE_PATH = "./data/neura.txt";  // Path to save/load tasks

    private ArrayList<Task> tasks = new ArrayList<>();  // Using ArrayList instead of array

    public static void main(String[] args) {
        Neura neura = new Neura();
        neura.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);

        printGreeting();

        loadTasks();  // Load tasks from file on startup

        while (true) {
            String userInput = scanner.nextLine().trim(); // Get user input

            if (userInput.equals(EXIT_COMMAND)) {
                printGoodbye();
                saveTasks();  // Save tasks to file before exit
                break;
            }
            try {
                handleCommand(userInput);
            } catch (NeuraException e) {
                System.out.println(SEPARATOR);
                System.out.println("OOPS!!! " + e.getMessage());
                System.out.println(SEPARATOR);
            }
        }

        scanner.close();
    }

    private void printGreeting() {
        System.out.println(SEPARATOR);
        System.out.println("Hello! I'm Neura");
        System.out.println("What can I do for you?");
        System.out.println(SEPARATOR);
    }

    private void printGoodbye() {
        System.out.println(SEPARATOR);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(SEPARATOR);
    }

    private void handleCommand(String userInput) throws NeuraException {
        if (userInput.equals(LIST_COMMAND)) {
            displayTasks();
        } else if (userInput.startsWith(MARK_COMMAND)) {
            markTaskAsDone(userInput);
        } else if (userInput.startsWith(UNMARK_COMMAND)) {
            unmarkTaskAsDone(userInput);
        } else if (userInput.startsWith(TODO_COMMAND)) {
            addTodoTask(userInput);
        } else if (userInput.startsWith(DEADLINE_COMMAND)) {
            addDeadlineTask(userInput);
        } else if (userInput.startsWith(EVENT_COMMAND)) {
            addEventTask(userInput);
        } else if (userInput.startsWith(DELETE_COMMAND)) {
            deleteTask(userInput);
        } else {
            throw new NeuraException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private void displayTasks() {
        System.out.println(SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        System.out.println(SEPARATOR);
    }

    private void markTaskAsDone(String userInput) throws NeuraException {
        int taskIndex = getTaskIndex(userInput);
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex).markAsDone();
            printTaskStatus("Nice! I've marked this task as done:", taskIndex);
        } else {
            throw new NeuraException("Invalid task number.");
        }
    }

    private void unmarkTaskAsDone(String userInput) throws NeuraException {
        int taskIndex = getTaskIndex(userInput);
        if (isValidTaskIndex(taskIndex)) {
            tasks.get(taskIndex).markAsNotDone();
            printTaskStatus("OK, I've marked this task as not done yet:", taskIndex);
        } else {
            throw new NeuraException("Invalid task number.");
        }
    }

    private int getTaskIndex(String userInput) throws NeuraException {
        try {
            return Integer.parseInt(userInput.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new NeuraException("Invalid task number format.");
        }
    }

    private boolean isValidTaskIndex(int index) {
        return index >= 0 && index < tasks.size();
    }

    private void printTaskStatus(String message, int taskIndex) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(tasks.get(taskIndex));
        System.out.println(SEPARATOR);
    }

    private void addTodoTask(String userInput) throws NeuraException {
        if (userInput.trim().equals(TODO_COMMAND)) {
            throw new NeuraException("The description of a todo cannot be empty.");
        }
        tasks.add(new Todo(userInput));  // Add a new Todo task to the list
        printTaskAddedMessage();
        saveTasks();  // Save tasks to file after adding
    }

    private void addDeadlineTask(String userInput) throws NeuraException {
        try {
            String taskDetails = userInput.substring(9).trim();
            String[] details = taskDetails.split(" /by ", 2);
            if (details.length < 2) throw new NeuraException("Invalid deadline format. Use: deadline [task] /by [time]");
            tasks.add(new Deadline(details[0], details[1]));
            printTaskAddedMessage();
            saveTasks();  // Save tasks to file after adding
        } catch (IndexOutOfBoundsException e) {
            throw new NeuraException("Invalid deadline format. Use: deadline [task] /by [time]");
        }
    }

    private void addEventTask(String userInput) throws NeuraException {
        try {
            String taskDetails = userInput.substring(6).trim();
            String[] details = taskDetails.split(" /from ", 2);
            if (details.length < 2) throw new NeuraException("Invalid event format. Use: event [task] /from [start] /to [end]");
            String[] dateDetails = details[1].split(" /to ", 2);
            if (dateDetails.length < 2) throw new NeuraException("Invalid event format. Use: event [task] /from [start] /to [end]");
            tasks.add(new Event(details[0], dateDetails[0], dateDetails[1]));
            printTaskAddedMessage();
            saveTasks();  // Save tasks to file after adding
        } catch (IndexOutOfBoundsException e) {
            throw new NeuraException("Invalid event format. Use: event [task] /from [start] /to [end]");
        }
    }

    private void printTaskAddedMessage() {
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(SEPARATOR);
    }

    private void deleteTask(String userInput) throws NeuraException {
        int taskIndex = getTaskIndex(userInput);
        if (isValidTaskIndex(taskIndex)) {
            Task removedTask = tasks.remove(taskIndex);
            System.out.println(SEPARATOR);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(SEPARATOR);
            saveTasks();  // Save tasks to file after deletion
        } else {
            throw new NeuraException("Invalid task number.");
        }
    }

    // Load tasks from file
    private void loadTasks() {
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

    // Parse a line into a Task object
    private Task parseTask(String line) {
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
    private void saveTasks() {
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
    private String taskToFileFormat(Task task) {
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
}
