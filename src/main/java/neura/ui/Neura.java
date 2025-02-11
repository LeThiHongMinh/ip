package neura.ui;

import neura.exception.NeuraException;
import neura.task.Deadline;
import neura.task.Event;
import neura.task.Task;
import neura.task.Todo;

import java.util.Scanner;

public class Neura {

    private static final int MAX_TASKS = 100;  // Maximum number of tasks
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String MARK_COMMAND = "mark";
    private static final String UNMARK_COMMAND = "unmark";
    private static final String TODO_COMMAND = "todo";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String EVENT_COMMAND = "event";
    private static final String TASK_LIST_FULL_MESSAGE = "Task list is full. Cannot add more tasks.";
    private static final String SEPARATOR = "____________________________________________________________";  // Constant for separator line

    private Task[] tasks = new Task[MAX_TASKS];
    private int taskCount = 0;

    public static void main(String[] args) {
        Neura neura = new Neura();
        neura.run();
    }

    private void run() {
        Scanner scanner = new Scanner(System.in);

        printGreeting();

        while (true) {
            String userInput = scanner.nextLine().trim(); // Get user input

            if (userInput.equals(EXIT_COMMAND)) {
                printGoodbye();
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

    private void handleCommand(String userInput) throws NeuraException{
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
        } else {
            throw new NeuraException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private void displayTasks() {
        System.out.println(SEPARATOR);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(tasks[i]);
        }
        System.out.println(SEPARATOR);
    }

    private void markTaskAsDone(String userInput) throws NeuraException{
        int taskIndex = getTaskIndex(userInput);
        if (isValidTaskIndex(taskIndex)) {
            tasks[taskIndex].markAsDone();
            printTaskStatus("Nice! I've marked this task as done:", taskIndex);
        } else {
            throw new NeuraException("Invalid task number.");
        }
    }

    private void unmarkTaskAsDone(String userInput) throws NeuraException{
        int taskIndex = getTaskIndex(userInput);
        if (isValidTaskIndex(taskIndex)) {
            tasks[taskIndex].markAsNotDone();
            printTaskStatus("OK, I've marked this task as not done yet:", taskIndex);
        } else {
            throw new NeuraException("Invalid task number.");
        }
    }

    private int getTaskIndex(String userInput) throws NeuraException{
        try {
            return Integer.parseInt(userInput.split(" ")[1]) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new NeuraException("Invalid task number format.");
        }
    }

    private boolean isValidTaskIndex(int index) {
        return index >= 0 && index < taskCount;
    }

    private void printTaskStatus(String message, int taskIndex) {
        System.out.println(SEPARATOR);
        System.out.println(message);
        System.out.println(tasks[taskIndex]);
        System.out.println(SEPARATOR);
    }

    private void addTodoTask(String userInput) throws NeuraException{
        if (userInput.trim().equals(TODO_COMMAND)) {
            throw new NeuraException("The description of a todo cannot be empty.");
        }
        if (taskCount < MAX_TASKS) {
            tasks[taskCount++] = new Todo(userInput);  // Create a new Todo task
            printTaskAddedMessage();
        } else {
            printTaskListFullMessage();
        }
    }

    private void addDeadlineTask(String userInput) throws NeuraException{
        try {
            String taskDetails = userInput.substring(9).trim();
            String[] details = taskDetails.split(" /by ", 2);
            if (details.length < 2) throw new NeuraException("Invalid deadline format. Use: deadline [task] /by [time]");
            if (taskCount < MAX_TASKS) {
                tasks[taskCount++] = new Deadline(details[0], details[1]);
                printTaskAddedMessage();
            } else {
                printTaskListFullMessage();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new NeuraException("Invalid deadline format. Use: deadline [task] /by [time]");
        }
    }

    private void addEventTask(String userInput) throws NeuraException{
        try {
            String taskDetails = userInput.substring(6).trim();
            String[] details = taskDetails.split(" /from ", 2);
            if (details.length < 2) throw new NeuraException("Invalid event format. Use: event [task] /from [start] /to [end]");
            String[] dateDetails = details[1].split(" /to ", 2);
            if (dateDetails.length < 2) throw new NeuraException("Invalid event format. Use: event [task] /from [start] /to [end]");
            if (taskCount < MAX_TASKS) {
                tasks[taskCount++] = new Event(details[0], dateDetails[0], dateDetails[1]);
                printTaskAddedMessage();
            } else {
                printTaskListFullMessage();
            }
        } catch (IndexOutOfBoundsException e) {
            throw new NeuraException("Invalid event format. Use: event [task] /from [start] /to [end]");
        }
    }

    private void printTaskAddedMessage() {
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[taskCount - 1]);
        System.out.println("Now you have " + taskCount + " tasks in the list");
        System.out.println(SEPARATOR);
    }

    private void printTaskListFullMessage() {
        System.out.println(SEPARATOR);
        System.out.println(TASK_LIST_FULL_MESSAGE);
        System.out.println(SEPARATOR);
    }
}
