package com.leadconsult.task.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ObjectNotValid extends RuntimeException {
    public ObjectNotValid(String message) {
        super(message);
    }
}

