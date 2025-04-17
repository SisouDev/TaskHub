package com.taskhub.taskhub.exceptions.taskmanagement;

import java.io.Serial;

public class TaskNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
