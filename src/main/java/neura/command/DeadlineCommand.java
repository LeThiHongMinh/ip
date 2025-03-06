package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;
import neura.task.Deadline;
import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private final String description;
    private final String deadlineTime;

    public DeadlineCommand(String description, String deadlineTime) {
        this.description = description;
        this.deadlineTime = deadlineTime;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        Deadline newTask = new Deadline(description, deadlineTime);
        tasks.add(newTask);
        ui.printTaskAddedMessage(tasks);
        storage.saveTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
