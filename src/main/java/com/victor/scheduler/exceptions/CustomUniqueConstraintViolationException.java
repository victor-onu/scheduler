package com.victor.scheduler.exceptions;


public class CustomUniqueConstraintViolationException extends FintrackException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CustomUniqueConstraintViolationException() {
	super("Data is not unique");
    }

    public CustomUniqueConstraintViolationException(String message) {
	super(message);
    }

}
