package neura.task;

/**
 * Represents an event task in the to-do list, which has a start time ("from") and an end time ("to").
 * Inherits from the {@link Task} class and adds additional attributes specific to events.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs a new Event task with the specified description, start time, and end time.
     * The task is initially marked as not done.
     *
     * @param description The description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the start time of the event.
     *
     * @return The start time of the event.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event.
     */
    public String getTo() {
        return to;
    }

    /**
     * Returns a string representation of the Event task, including its type, description, and time range.
     * The format is "[E][status] description (from: [start time] to: [end time])".
     *
     * @return A string representing the Event task in the format "[E][status] description (from: [start time] to: [end time])".
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
