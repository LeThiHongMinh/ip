package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        tasks.get(taskIndex).markAsNotDone();
        ui.printTaskStatus("OK, I've marked this task as not done yet:", taskIndex, tasks);
        storage.saveTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
