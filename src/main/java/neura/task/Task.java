package neura.task;

/**
 * Represents a task in the to-do list with a description and completion status.
 * Allows marking the task as done or not done and provides methods to retrieve task information.
 */
public class Task {
    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a new Task with the specified description. The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return True if the task is done, otherwise false.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns the icon representing the completion status of the task.
     *
     * @return "X" if the task is done, otherwise " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the description of the task.
     *
     * @return The description of the task.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns a string representation of the task, including its status and description.
     *
     * @return A string representing the task in the format "[status] description".
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
}

