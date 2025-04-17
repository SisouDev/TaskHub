package com.taskhub.taskhub.exceptions.organizational;

import java.io.Serial;

public class RoleNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public RoleNotFoundException(String message) {
        super(message);
    }

    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
