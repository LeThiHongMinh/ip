package neura.parser;

import neura.command.*;
import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;
import neura.exception.NeuraException;

import java.util.ArrayList;

/**
 * Parses user input and returns the appropriate Command object.
 * This class is responsible for interpreting the user's input and transforming it into a command that the application can execute.
 */
public class Parser {

    /**
     * Parses the full command entered by the user and returns the corresponding Command object.
     *
     * @param fullCommand The full user command input as a string.
     * @return A Command object representing the parsed command.
     * @throws NeuraException If the command is invalid or malformatted.
     */
    public static Command parse(String fullCommand) throws NeuraException {
        String command = fullCommand.split(" ")[0].toLowerCase();  // Extract the command type (e.g., "todo", "deadline")
        String commandData = fullCommand.substring(command.length()).trim();  // Get the rest of the command
        if (command.isEmpty()) {
            throw new NeuraException("Command cannot be empty.");
        }

        switch (command) {
        case "todo":
            return parseTodoCommand(commandData);
        case "deadline":
            return parseDeadlineCommand(commandData);
        case "event":
            return parseEventCommand(commandData);
        case "mark":
            return parseMarkCommand(commandData);
        case "unmark":
            return parseUnmarkCommand(commandData);
        case "list":
            return new ListCommand();
        case "delete":
            return parseDeleteCommand(commandData);
        case "bye":
            return new ExitCommand();
        case "find":
            return parseFindCommand(commandData);
        default:
            throw new NeuraException("Invalid command! Please try again.");
        }
    }

    /**
     * Parses the "todo" command and creates a TodoCommand.
     *
     * @param data The description of the todo task.
     * @return A TodoCommand object.
     * @throws NeuraException If the description is empty.
     */
    private static Command parseTodoCommand(String data) throws NeuraException {
        if (data.isEmpty()) {
            throw new NeuraException("The description of a todo cannot be empty.");
        }
        return new TodoCommand(data);
    }

    /**
     * Parses the "deadline" command and creates a DeadlineCommand.
     *
     * @param data The description of the deadline task and the due time.
     * @return A DeadlineCommand object.
     * @throws NeuraException If the format of the deadline command is incorrect.
     */
    private static Command parseDeadlineCommand(String data) throws NeuraException {
        String[] parts = data.split(" /by ");
        if (parts.length < 2) {
            throw new NeuraException("Invalid deadline format! Use: deadline [task] /by [time].");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        return new DeadlineCommand(description, by);
    }

    /**
     * Parses the "event" command and creates an EventCommand.
     *
     * @param data The description of the event task, start time, and end time.
     * @return An EventCommand object.
     * @throws NeuraException If the format of the event command is incorrect.
     */
    private static Command parseEventCommand(String data) throws NeuraException {
        String[] parts = data.split(" /from ");
        if (parts.length < 2) {
            throw new NeuraException("Invalid event format! Use: event [task] /from [start] /to [end].");
        }
        String description = parts[0].trim();
        String[] dateParts = parts[1].split(" /to ");
        if (dateParts.length < 2) {
            throw new NeuraException("Invalid event format! Use: event [task] /from [start] /to [end].");
        }
        String from = dateParts[0].trim();
        String to = dateParts[1].trim();
        return new EventCommand(description, from, to);
    }

    /**
     * Parses the "mark" command and creates a MarkCommand.
     *
     * @param data The task number to be marked as done.
     * @return A MarkCommand object.
     * @throws NeuraException If the task number is invalid or cannot be parsed.
     */
    private static Command parseMarkCommand(String data) throws NeuraException {
        try {
            int taskIndex = Integer.parseInt(data.trim()) - 1;  // Convert to zero-based index
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new NeuraException("Invalid task number! Please specify the task number to mark.");
        }
    }

    /**
     * Parses the "unmark" command and creates an UnmarkCommand.
     *
     * @param data The task number to be unmarked as done.
     * @return An UnmarkCommand object.
     * @throws NeuraException If the task number is invalid or cannot be parsed.
     */
    private static Command parseUnmarkCommand(String data) throws NeuraException {
        try {
            int taskIndex = Integer.parseInt(data.trim()) - 1;  // Convert to zero-based index
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new NeuraException("Invalid task number! Please specify the task number to unmark.");
        }
    }

    /**
     * Parses the "delete" command and creates a DeleteCommand.
     *
     * @param data The task number to be deleted.
     * @return A DeleteCommand object.
     * @throws NeuraException If the task number is invalid or cannot be parsed.
     */
    private static Command parseDeleteCommand(String data) throws NeuraException {
        try {
            int taskIndex = Integer.parseInt(data.trim()) - 1;  // Convert to zero-based index
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new NeuraException("Invalid task number! Please specify the task number to delete.");
        }
    }

    /**
     * Parses the "find" command and creates a FindCommand.
     *
     * @param data The search keyword.
     * @return A FindCommand object.
     * @throws NeuraException If the search keyword is empty.
     */
    private static Command parseFindCommand(String data) throws NeuraException {
        if (data.isEmpty()) {
            throw new NeuraException("Search keyword cannot be empty.");
        }
        return new FindCommand(data);
    }
}
