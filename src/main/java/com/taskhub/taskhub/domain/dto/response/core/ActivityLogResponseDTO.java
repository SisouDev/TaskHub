package com.taskhub.taskhub.domain.dto.response.core;

import java.time.LocalDateTime;

public record ActivityLogResponseDTO(
        Long id,
        String activity,
        LocalDateTime date,
        String entity,
        Long entityId,
        Long userId
) {
}
