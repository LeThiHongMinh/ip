package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;
import neura.task.Deadline;
import java.util.ArrayList;

/**
 * Represents a command to add a deadline task to the task list.
 * The deadline task includes a description and a deadline time.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String deadlineTime;

    /**
     * Constructs a DeadlineCommand object with a task description and a deadline time.
     *
     * @param description The description of the task.
     * @param deadlineTime The deadline time for the task.
     */
    public DeadlineCommand(String description, String deadlineTime) {
        this.description = description;
        this.deadlineTime = deadlineTime;
    }

    /**
     * Executes the command by adding a new deadline task to the task list.
     * The task is displayed to the user, and the updated task list is saved.
     *
     * @param tasks The list of tasks where the new deadline task will be added.
     * @param ui The user interface used to print the task added message.
     * @param storage The storage object used to save the updated list of tasks.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        Deadline newTask = new Deadline(description, deadlineTime);
        tasks.add(newTask);
        ui.printTaskAddedMessage(tasks);
        storage.saveTasks(tasks);
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

