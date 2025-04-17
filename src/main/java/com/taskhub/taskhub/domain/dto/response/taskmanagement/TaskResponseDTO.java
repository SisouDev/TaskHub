package com.taskhub.taskhub.domain.dto.response.taskmanagement;

import com.taskhub.taskhub.domain.enums.Status;

import java.time.LocalDateTime;
import java.util.Set;

public record TaskResponseDTO(
        Long id,
        String title,
        String description,
        LocalDateTime createdAt,
        LocalDateTime deadline,
        Status status,
        String projectName,
        Set<String> tagNames,
        Set<String> categoryNames,
        Set<String> userNames
) {
}
