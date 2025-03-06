package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

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

    @Override
    public boolean isExit() {
        return false;  // Find command doesn't exit the program
    }
}
