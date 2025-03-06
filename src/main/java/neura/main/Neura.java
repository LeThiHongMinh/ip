package neura.main;

import neura.exception.NeuraException;
import neura.parser.Parser;
import neura.storage.Storage;
import neura.task.Task;
import neura.ui.Ui;
import neura.command.Command;

import java.util.ArrayList;

public class Neura {
    private Storage storage;
    private ArrayList<Task> tasks;
    private Ui ui;

    public Neura() {
        ui = new Ui();
        storage = new Storage();
        tasks = new ArrayList<>();  // Initialize tasks as an ArrayList
        try {
            storage.loadTasks(tasks);  // Load tasks into the ArrayList
        } catch (Exception e) {
            ui.showLoadingError();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                isExit = command.isExit();
                command.execute(tasks, ui, storage);  // Now execute returns a boolean
            } catch (NeuraException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Neura().run();
    }
}
