package neura.task;

/**
 * Represents a task of type "ToDo" in the to-do list.
 * Inherits from the {@link Task} class and adds a specific identifier for ToDo tasks.
 */
public class Todo extends Task {

    /**
     * Constructs a new ToDo task with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the ToDo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the ToDo task, including its type and description.
     * The format is "[T][status] description".
     *
     * @return A string representing the ToDo task in the format "[T][status] description".
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

