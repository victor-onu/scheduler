package com.victor.scheduler.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FintrackException extends RuntimeException{

    protected String message;
    
    public FintrackException(String message) {
        this.message=message;
    }
}
