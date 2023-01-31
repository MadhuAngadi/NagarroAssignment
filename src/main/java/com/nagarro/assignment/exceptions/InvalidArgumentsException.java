package com.nagarro.assignment.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidArgumentsException extends RuntimeException {
    public InvalidArgumentsException(String msg) {
        super(msg);
    }

    public InvalidArgumentsException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public InvalidArgumentsException(Throwable cause) {
        super(cause);
    }
}
