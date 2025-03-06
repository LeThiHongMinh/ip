package neura.command;

import neura.ui.Ui;
import neura.storage.Storage;
import neura.task.Task;

import java.util.ArrayList;

public abstract class Command {
    public abstract void execute(ArrayList<Task> tasks, Ui ui, Storage storage);
    public abstract boolean isExit();
}