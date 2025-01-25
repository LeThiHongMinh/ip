import java.util.Scanner;

public class Neura {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Store tasks
        Task[] tasks = new Task[100];  // Array to store up to 100 tasks
        int taskCount = 0;  // Keep track of how many tasks are stored

        // Display initial greeting
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Neura");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        // Loop to keep listening for user input
        while (true) {
            String userInput = scanner.nextLine(); // Get user input

            // Exit the loop if the user types "bye"
            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            // If the user types "list", display the stored tasks
            if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription());
                }
                System.out.println("____________________________________________________________");
            }
            // Mark task as done
            else if (userInput.startsWith("mark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  [" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());
                    System.out.println("____________________________________________________________");
                }
            }
            // Unmark task as done
            else if (userInput.startsWith("unmark")) {
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                if (taskIndex >= 0 && taskIndex < taskCount) {
                    tasks[taskIndex].markAsNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  [" + tasks[taskIndex].getStatusIcon() + "] " + tasks[taskIndex].getDescription());
                    System.out.println("____________________________________________________________");
                }
            }
            // Add a new task
            else {
                if (taskCount < tasks.length) {
                    tasks[taskCount] = new Task(userInput);  // Create a new task
                    taskCount++;  // Increment the task count
                    System.out.println("____________________________________________________________");
                    System.out.println("added: " + userInput);
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Task list is full. Cannot add more tasks.");
                    System.out.println("____________________________________________________________");
                }
            }
        }

        scanner.close();
    }
}

// Task class to represent tasks
class Task {
    private String description;
    private boolean isDone;

    // Constructor
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    // Mark task as done
    public void markAsDone() {
        isDone = true;
    }

    // Mark task as not done
    public void markAsNotDone() {
        isDone = false;
    }

    // Get task status icon (either X for done or space for not done)
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    // Get task description
    public String getDescription() {
        return description;
    }
}
