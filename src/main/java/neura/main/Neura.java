package neura.main;

import neura.exception.NeuraException;
import neura.parser.Parser;
import neura.storage.Storage;
import neura.task.Task;
import neura.ui.Ui;
import neura.command.Command;

import java.util.ArrayList;

/**
 * Main class that runs the Neura task manager application.
 * This class initializes the necessary components (UI, storage, and tasks) and executes user commands in a loop.
 */
public class Neura {
    private Storage storage;
    private ArrayList<Task> tasks;
    private Ui ui;

    /**
     * Initializes the Neura application with the necessary components.
     * This constructor sets up the UI, storage, and task list, and attempts to load saved tasks from the storage file.
     */
    public Neura() {
        ui = new Ui();
        storage = new Storage();
        tasks = new ArrayList<>();  // Initialize tasks as an ArrayList
        try {
            storage.loadTasks(tasks);  // Load tasks into the ArrayList
        } catch (Exception e) {
            ui.showLoadingError();  // Show an error message if loading fails
        }
    }

    /**
     * Runs the main loop of the Neura application.
     * This method repeatedly prompts the user for commands and executes them until the user chooses to exit.
     */
    public void run() {
        ui.showWelcome();  // Display the welcome message
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();  // Read the full user command
                Command command = Parser.parse(fullCommand);  // Parse the command
                isExit = command.isExit();  // Check if the command signals the application to exit
                command.execute(tasks, ui, storage);  // Execute the command, passing the tasks, UI, and storage
            } catch (NeuraException e) {
                ui.showError(e.getMessage());  // Show error message if the command is invalid
            }
        }
    }

    /**
     * The main entry point of the Neura application.
     *
     * @param args Command-line arguments (not used in this version).
     */
    public static void main(String[] args) {
        new Neura().run();  // Create and run a new instance of the Neura application
    }
}
