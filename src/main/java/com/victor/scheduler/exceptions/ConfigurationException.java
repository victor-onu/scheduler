package com.victor.scheduler.exceptions;


public class ConfigurationException extends FintrackException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ConfigurationException() {
	super("Data is not unique");
    }

    public ConfigurationException(String message) {
	super(message);
    }

}
