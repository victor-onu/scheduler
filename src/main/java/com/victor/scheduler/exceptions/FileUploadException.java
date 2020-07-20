package com.victor.scheduler.exceptions;

public class FileUploadException extends RuntimeException {
    
    private String message;

    /**
     * @param message
     */
    public FileUploadException(String message,Exception ex) {
	super();
	this.message = message;
    }
    
    
    

}
