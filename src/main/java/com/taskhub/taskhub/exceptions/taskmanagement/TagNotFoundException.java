package com.taskhub.taskhub.exceptions.taskmanagement;

import java.io.Serial;

public class TagNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TagNotFoundException(String message) {
        super(message);
    }

    public TagNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
