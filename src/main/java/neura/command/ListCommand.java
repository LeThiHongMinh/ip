package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;
import java.util.ArrayList;

/**
 * Represents a command to list all tasks in the task list.
 * This command will display all tasks currently in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     * @param ui The user interface used to show the list of tasks.
     * @param storage The storage object (not used in this command).
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.displayTasks(tasks);
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
