package com.taskhub.taskhub.exceptions.project;

import java.io.Serial;

public class ProjectNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;
    public ProjectNotFoundException(String message) {
        super(message);
    }

    public ProjectNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
