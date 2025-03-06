package neura.task;

/**
 * Represents a deadline task in the to-do list, which has a specific due date.
 * Inherits from the {@link Task} class and adds an additional attribute for the deadline.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a new Deadline task with the specified description and due date.
     * The task is initially marked as not done.
     *
     * @param description The description of the deadline task.
     * @param by The due date for the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the due date of the deadline task.
     *
     * @return The due date of the deadline task.
     */
    public String getBy() {
        return by;
    }

    /**
     * Returns a string representation of the Deadline task, including its type, description, and due date.
     * The format is "[D][status] description (by: [due date])".
     *
     * @return A string representing the Deadline task in the format "[D][status] description (by: [due date])".
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
