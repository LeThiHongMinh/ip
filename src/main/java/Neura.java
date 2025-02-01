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
                    System.out.println(tasks[i].toString());
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
                    System.out.println(tasks[taskIndex].toString());
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
                    System.out.println(tasks[taskIndex].toString());
                    System.out.println("____________________________________________________________");
                }
            }
            // Add a new todo
            else if (userInput.startsWith("todo")){
                if (taskCount < tasks.length) {
                    tasks[taskCount] = new Todo(userInput);  // Create a new task
                    taskCount++;  // Increment the task count
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount - 1].toString());
                    System.out.println("Now you have " + taskCount + " tasks in the list");
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Task list is full. Cannot add more tasks.");
                    System.out.println("____________________________________________________________");
                }
            }

            // Add new deadline
            else if (userInput.startsWith("deadline")){
                if (taskCount < tasks.length) {
                    String[] parts = userInput.split(" ", 2);
                    // Extracting task types and details
                    String taskType = parts[0];
                    String taskDetails = parts[1];

                    // Extracting description and by
                    String[] details = taskDetails.split(" /by ", 2);
                    String description = details[0];
                    String by = details[1];
                    tasks[taskCount] = new Deadline(description, by);
                    taskCount++;  // Increment the task count
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount - 1].toString());
                    System.out.println("Now you have " + taskCount + " tasks in the list");
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Task list is full. Cannot add more tasks.");
                    System.out.println("____________________________________________________________");
                }
            }

            else if (userInput.startsWith("event")){
                if (taskCount < tasks.length) {
                    String[] parts = userInput.split(" ", 2);
                    // Extracting task types and details
                    String taskType = parts[0];
                    String taskDetails = parts[1];

                    // Extracting description and by
                    String[] details = taskDetails.split(" /from ", 2);
                    String description = details[0];
                    String time = details[1];
                    String[] dateDetails = time.split(" /to", 2);
                    String from = dateDetails[0];
                    String to = dateDetails[1];
                    tasks[taskCount] = new Event(description, from, to);
                    taskCount++;  // Increment the task count
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks[taskCount - 1].toString());
                    System.out.println("Now you have " + taskCount + " tasks in the list");
                    System.out.println("____________________________________________________________");
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println("Task list is full. Cannot add more tasks.");
                    System.out.println("____________________________________________________________");
                }
            }

            else {
                System.out.println("____________________________________________________________");
                System.out.println(userInput);
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
