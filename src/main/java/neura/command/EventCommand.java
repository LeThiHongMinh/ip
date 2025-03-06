package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;
import neura.task.Event;

import java.util.ArrayList;

/**
 * Represents a command to create an event and add it to the task list.
 * The event's description, start time, and end time are provided when constructing the command.
 */
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    /**
     * Constructs an EventCommand object with the task description, start time, and end time.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the event command by creating a new event and adding it to the task list.
     * The new event is saved to the storage after it's added to the task list.
     *
     * @param tasks The list of tasks where the new event will be added.
     * @param ui The user interface used to display messages to the user.
     * @param storage The storage object used to save the updated task list.
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        Event newEvent = new Event(description, from, to);
        tasks.add(newEvent);
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

