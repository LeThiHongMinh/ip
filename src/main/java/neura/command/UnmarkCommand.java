package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;
import neura.exception.NeuraException;
import java.util.ArrayList;

/**
 * Represents a command to unmark a task as not done in the task list.
 * This command takes a task index, verifies the index, and unmarks the corresponding task.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to be unmarked.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the unmark command by unmarking the task at the specified index.
     * If the task index is valid, it will mark the task as not done.
     * The updated task list is saved to storage after the operation.
     *
     * @param tasks The list of tasks where the task to be unmarked is located.
     * @param ui The user interface used to display a status message to the user.
     * @param storage The storage object used to save the updated task list.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            // Check if taskIndex is valid
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new NeuraException("Task index out of range. Please provide a valid task index.");
            }

            // If valid, mark the task as not done
            tasks.get(taskIndex).markAsNotDone();
            ui.printTaskStatus("OK, I've marked this task as not done yet:", taskIndex, tasks);
            storage.saveTasks(tasks);

        } catch (NeuraException e) {
            // Handle the exception and show the error message
            ui.showError(e.getMessage());
        }
    }

    /**
     * Indicates that this command does not exit the application.
     *
     * @return false, signaling that the application should continue running after executing this command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
