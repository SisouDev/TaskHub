package com.taskhub.taskhub.exceptions.core;

import java.io.Serial;

public class NotificationNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NotificationNotFoundException(String message) {
        super(message);
    }

    public NotificationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
