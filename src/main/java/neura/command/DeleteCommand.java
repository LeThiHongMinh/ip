package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;
import java.util.ArrayList;

/**
 * Represents a command to delete a task from the task list.
 * The task to be deleted is specified by its index in the list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand object with the task index.
     *
     * @param taskIndex The index of the task to be deleted from the task list.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the delete command by removing the task at the specified index.
     * If the task list is empty or the index is invalid, an error message is displayed.
     * The task list is saved after deletion.
     *
     * @param tasks The list of tasks where the task will be removed.
     * @param ui The user interface used to display messages to the user.
     * @param storage The storage object used to save the updated task list.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showError("OOPS!!! Your task list is empty.");
            return;
        }

        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            ui.showError("OOPS!!! Invalid task index! Please enter a number between 1 and " + tasks.size());
            return;
        }

        Task removedTask = tasks.remove(taskIndex); // Correctly remove task
        ui.printRemovedTask(removedTask, tasks);

        storage.saveTasks(tasks); // Save updated tasks list
    }

    /**
     * Indicates that this command does not exit the application.
     *
     * @return false, since this command does not exit the application.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
