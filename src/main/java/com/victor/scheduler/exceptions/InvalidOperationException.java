package com.victor.scheduler.exceptions;

public class InvalidOperationException extends FintrackException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public InvalidOperationException() {
	super("Data is not unique");
    }

    public InvalidOperationException(String message) {
	super(message);
    }

    public InvalidOperationException(String message, Exception ex) {
	super(message);
    }

}
