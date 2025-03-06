package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;

import java.util.ArrayList;

/**
 * Abstract class representing a command in the Neura application.
 * All specific command types (e.g., Add, Delete, Mark) should extend this class and implement its methods.
 */
public abstract class Command {

    /**
     * Executes the command, affecting the list of tasks, user interface, and storage.
     *
     * @param tasks The list of tasks that the command will operate on.
     * @param ui The user interface that interacts with the user.
     * @param storage The storage object that manages task data persistence.
     */
    public abstract void execute(ArrayList<Task> tasks, Ui ui, Storage storage);

    /**
     * Determines whether the command will exit the application.
     *
     * @return true if the command should terminate the program; false otherwise.
     */
    public abstract boolean isExit();
}
