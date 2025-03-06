package neura.parser;

import neura.command.*;
import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;
import neura.exception.NeuraException;

import java.util.ArrayList;

public class Parser {

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
        default:
            throw new NeuraException("Invalid command! Please try again.");
        }
    }

    private static Command parseTodoCommand(String data) throws NeuraException {
        if (data.isEmpty()) {
            throw new NeuraException("The description of a todo cannot be empty.");
        }
        return new TodoCommand(data);
    }

    private static Command parseDeadlineCommand(String data) throws NeuraException {
        String[] parts = data.split(" /by ");
        if (parts.length < 2) {
            throw new NeuraException("Invalid deadline format! Use: deadline [task] /by [time].");
        }
        String description = parts[0].trim();
        String by = parts[1].trim();
        return new DeadlineCommand(description, by);
    }

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

    private static Command parseMarkCommand(String data) throws NeuraException {
        try {
            int taskIndex = Integer.parseInt(data.trim()) - 1;  // Convert to zero-based index
            return new MarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new NeuraException("Invalid task number! Please specify the task number to mark.");
        }
    }

    private static Command parseUnmarkCommand(String data) throws NeuraException {
        try {
            int taskIndex = Integer.parseInt(data.trim()) - 1;  // Convert to zero-based index
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new NeuraException("Invalid task number! Please specify the task number to unmark.");
        }
    }

    private static Command parseDeleteCommand(String data) throws NeuraException {
        try {
            int taskIndex = Integer.parseInt(data.trim()) - 1;  // Convert to zero-based index
            return new DeleteCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new NeuraException("Invalid task number! Please specify the task number to delete.");
        }
    }
}
