package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;
import java.util.ArrayList;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        if (tasks.isEmpty()) {
            ui.showError("OOPS!!! Your task list is empty.");
            return;
        }

        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            ui.showError("OOPS!!! Invalid task index! Please enter a number between 1 and " + tasks.size());
            return;
        }

        Task removedTask = tasks.remove(taskIndex); // Correctly remove task
        ui.printRemovedTask(removedTask, tasks);

        storage.saveTasks(tasks); // Save updated tasks list
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
