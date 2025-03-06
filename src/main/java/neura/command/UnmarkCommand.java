package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;
import neura.exception.NeuraException;
import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        try {
            // Check if taskIndex is valid
            if (taskIndex < 0 || taskIndex >= tasks.size()) {
                throw new NeuraException("Task index out of range. Please provide a valid task index.");
            }

            // If valid, mark the task as not done
            tasks.get(taskIndex).markAsNotDone();
            ui.printTaskStatus("OK, I've marked this task as not done yet:", taskIndex, tasks);
            storage.saveTasks(tasks);

        } catch (NeuraException e) {
            // Handle the exception and show the error message
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
