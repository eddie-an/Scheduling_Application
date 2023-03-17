package edu.ucalgary.oop;

public class TooManyEventsException extends Exception {
    public TooManyEventsException() {
        super();
    }

    public TooManyEventsException(String message) {
        super(message);
    }
}
