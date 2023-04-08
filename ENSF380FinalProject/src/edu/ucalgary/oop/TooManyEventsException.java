package edu.ucalgary.oop;

/**
 * TooManyEventsException is a class which extends the Exception class and represents an exception
 * when the time it takes to complete a set of events exceeds the one hour time limit.
 * @author     Edward An, Karam Baroud, Evan Barker, Jad Khalil
 * @version    1.0
 * @since      1.0
 */
public class TooManyEventsException extends Exception {

    /**
     * This is the constructor for the TooManyEventsException class.
     */
    public TooManyEventsException() {
        super();
    }

    /**
     * This is the constructor for the TooManyEventsException class with a message argument.
     * @param message   The message to be displayed when the exception is thrown.
     */
    public TooManyEventsException(String message) {
        super(message);
    }
}
