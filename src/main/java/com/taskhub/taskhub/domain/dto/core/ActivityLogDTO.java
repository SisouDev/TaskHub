package com.taskhub.taskhub.domain.dto.core;

import java.time.LocalDateTime;

public record ActivityLogDTO(Long id,
                             String activity,
                             LocalDateTime date,
                             String entity,
                             Long entityId,
                             Long userId) {
}
