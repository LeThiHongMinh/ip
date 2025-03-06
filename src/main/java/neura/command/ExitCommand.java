package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;
import java.util.ArrayList;

/**
 * Represents a command to exit the application.
 * When executed, this command displays a goodbye message to the user and signals the application to terminate.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying a goodbye message to the user.
     * This indicates that the application is about to exit.
     *
     * @param tasks The list of tasks (not used in this command).
     * @param ui The user interface used to display the goodbye message.
     * @param storage The storage object (not used in this command).
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    /**
     * Indicates that this command will exit the application.
     *
     * @return true, signaling that the application should terminate after executing this command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
