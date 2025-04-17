package com.taskhub.taskhub.domain.dto.request.core;

import java.time.LocalDateTime;

public record NotificationRequestDTO(
        String message,
        Boolean read,
        LocalDateTime sendDate,
        Long recipientId
) {
}
