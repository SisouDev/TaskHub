package com.taskhub.taskhub.domain.dto.response.core;

import java.time.LocalDateTime;

public record NotificationResponseDTO(
        Long id,
        String message,
        Boolean read,
        LocalDateTime sendDate,
        Long recipientId
) {
}
