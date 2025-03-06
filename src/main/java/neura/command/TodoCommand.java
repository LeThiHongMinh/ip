package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;
import neura.task.Todo;

import java.util.ArrayList;

/**
 * Represents a command to add a new Todo task to the task list.
 * This command creates a Todo task with the provided description and adds it to the task list.
 */
public class TodoCommand extends Command {
    private final String description;

    /**
     * Constructs a TodoCommand with the specified task description.
     *
     * @param description The description of the Todo task to be added.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the todo command by creating a new Todo task, adding it to the task list,
     * and saving the updated task list to storage.
     *
     * @param tasks The list of tasks to which the new Todo task will be added.
     * @param ui The user interface used to display a confirmation message.
     * @param storage The storage object used to save the updated task list.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        Task newTask = new Todo(description);
        tasks.add(newTask);
        ui.printTaskAddedMessage(tasks);
        storage.saveTasks(tasks);
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

