package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;
import neura.task.Event;

import java.util.ArrayList;

public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String description, String from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        Event newEvent = new Event(description, from, to);
        tasks.add(newEvent);
        ui.printTaskAddedMessage(tasks);
        storage.saveTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
