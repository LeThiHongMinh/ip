package neura.exception;

/**
 * Custom exception class for handling specific errors in the Neura application.
 * This exception is thrown when an error occurs related to the application logic, such as invalid commands.
 */
public class NeuraException extends Exception {

    /**
     * Constructor to create a NeuraException with a specific error message.
     *
     * @param message The error message to be associated with the exception.
     */
    public NeuraException(String message) {
        super(message);  // Pass the error message to the parent class (Exception)
    }
}
