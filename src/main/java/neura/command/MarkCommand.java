package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;
import neura.exception.NeuraException;
import java.util.ArrayList;

/**
 * Represents a command to mark a task as done in the task list.
 * This command marks a specific task by its index as completed.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param taskIndex The index of the task to mark as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the mark command by marking the task at the specified index as done.
     * It also updates the task list and saves it to storage.
     * If the task index is invalid, an error message will be shown.
     *
     * @param tasks The list of tasks that the task to be marked belongs to.
     * @param ui The user interface used to show the task status.
     * @param storage The storage object used to save the updated task list.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            // Check if taskIndex is valid
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new NeuraException("Task index out of range. Please provide a valid task index.");
            }

            // If valid, mark the task as done
            tasks.get(taskIndex).markAsDone();
            ui.printTaskStatus("Nice! I've marked this task as done:", taskIndex, tasks);
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
