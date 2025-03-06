package neura.ui;

import neura.task.Task;
import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    public static final String SEPARATOR = "____________________________________________________________";  // Constant for separator line
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Neura");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void displayTasks(ArrayList<Task> tasks) {
        showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    public void printTaskAddedMessage(ArrayList<Task> tasks) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        showLine();
    }

    public void showGoodbye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        showLine();
        System.out.println("OOPS!!! " + message);
        showLine();
    }

    public void showLoadingError() {
        showLine();
        System.out.println("Error loading tasks from file.");
        showLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }
    public void printTaskStatus(String message, int taskIndex, ArrayList<Task> tasks) {
        showLine();
        System.out.println(message);
        System.out.println(tasks.get(taskIndex));
        showLine();
    }

    public void printRemovedTask(Task removedTask, ArrayList<Task> tasks) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        showLine();
    }

    public void printFoundTasks(ArrayList<Task> matchingTasks) {
        showLine();
        if (matchingTasks.isEmpty()) {
            System.out.println("No tasks found with the given keyword.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + "." + matchingTasks.get(i));
            }
        }
        showLine();
    }
}
