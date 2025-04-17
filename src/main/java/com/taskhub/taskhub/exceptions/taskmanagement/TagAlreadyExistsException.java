package com.taskhub.taskhub.exceptions.taskmanagement;

import java.io.Serial;

public class TagAlreadyExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public TagAlreadyExistsException(String message) {
        super(message);
    }

    public TagAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
