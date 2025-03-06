package neura.ui;

import neura.task.Task;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Handles the user interface interactions with the user in the application.
 * Responsible for displaying messages, tasks, and prompts.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructor that initializes the scanner for reading user input.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Neura");
        System.out.println("What can I do for you?");
        showLine();
    }

    /**
     * Displays all the tasks currently in the list.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void displayTasks(ArrayList<Task> tasks) {
        showLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        showLine();
    }

    /**
     * Prints a message confirming that a task has been added to the task list.
     *
     * @param tasks The list of tasks that contains the newly added task.
     */
    public void printTaskAddedMessage(ArrayList<Task> tasks) {
        showLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks.get(tasks.size() - 1));
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        showLine();
    }

    /**
     * Displays a goodbye message when the user exits the application.
     */
    public void showGoodbye() {
        showLine();
        System.out.println("Bye. Hope to see you again soon!");
        showLine();
    }

    /**
     * Prints a line separator to separate sections of the output.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        showLine();
        System.out.println("OOPS!!! " + message);
        showLine();
    }

    /**
     * Displays a loading error message when tasks fail to load from a file.
     */
    public void showLoadingError() {
        showLine();
        System.out.println("Error loading tasks from file.");
        showLine();
    }

    /**
     * Reads the next line of user input from the console.
     *
     * @return The command inputted by the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Prints the status of a task (marked as done or not done) with its details.
     *
     * @param message The message indicating the status change of the task.
     * @param taskIndex The index of the task in the list.
     * @param tasks The list of tasks to access the task at the given index.
     */
    public void printTaskStatus(String message, int taskIndex, ArrayList<Task> tasks) {
        showLine();
        System.out.println(message);
        System.out.println(tasks.get(taskIndex));
        showLine();
    }

    /**
     * Prints a message confirming that a task has been removed from the task list.
     *
     * @param removedTask The task that was removed.
     * @param tasks The list of tasks after removal to update the task count.
     */
    public void printRemovedTask(Task removedTask, ArrayList<Task> tasks) {
        showLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        showLine();
    }

    /**
     * Displays all the tasks that match a given keyword.
     *
     * @param matchingTasks The list of tasks that match the keyword.
     */
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
