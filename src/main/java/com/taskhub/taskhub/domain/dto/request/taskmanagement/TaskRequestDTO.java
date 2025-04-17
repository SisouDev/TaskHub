package com.taskhub.taskhub.domain.dto.request.taskmanagement;

import com.taskhub.taskhub.domain.enums.Status;

import java.time.LocalDateTime;
import java.util.Set;

public record TaskRequestDTO(
        String title,
        String description,
        LocalDateTime deadline,
        Status status,
        Long projectId,
        Set<String> tagNames,
        Set<String> categoryNames,
        Set<Long> userIds
) {
}
