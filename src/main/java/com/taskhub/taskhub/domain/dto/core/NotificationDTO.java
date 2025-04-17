package com.taskhub.taskhub.domain.dto.core;

import java.time.LocalDateTime;

public record NotificationDTO(Long id,
                              String message,
                              Boolean read,
                              LocalDateTime sendDate,
                              Long recipientId) {
}
