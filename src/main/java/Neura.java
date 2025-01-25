import java.util.Scanner;

public class Neura {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

            // Echo the user input
            System.out.println("____________________________________________________________");
            System.out.println(userInput);
            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }
}
