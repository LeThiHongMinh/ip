package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;
import java.util.ArrayList;

/**
 * Represents a command to search for tasks by a given keyword.
 * This command filters tasks that contain the specified keyword in their description
 * and displays them to the user.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Creates a FindCommand with the specified keyword for searching tasks.
     *
     * @param keyword The keyword used to search for matching tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks that contain the specified keyword in their description.
     * Displays the found tasks to the user.
     *
     * @param tasks The list of tasks to search through.
     * @param ui The user interface used to display the matching tasks.
     * @param storage The storage object (not used in this command).
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        // Loop through the tasks to find matching tasks by keyword
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }

        // Call the UI method to display the found tasks
        ui.printFoundTasks(matchingTasks);
    }

    /**
     * Indicates that this command does not exit the application.
     *
     * @return false, signaling that the application should continue running after executing this command.
     */
    @Override
    public boolean isExit() {
        return false;  // Find command doesn't exit the program
    }
}
