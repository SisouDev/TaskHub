package com.taskhub.taskhub.domain.dto.request.core;

public record ActivityLogRequestDTO(
        String activity,
        String entity,
        Long entityId,
        Long userId
) {
}
