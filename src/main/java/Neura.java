import java.util.Scanner;

public class Neura {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Store tasks
        String[] tasks = new String[100];  // Array to store up to 100 tasks
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
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else {
                // Add the new task to the list
                if (taskCount < tasks.length) {
                    tasks[taskCount] = userInput;  // Store the task
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
