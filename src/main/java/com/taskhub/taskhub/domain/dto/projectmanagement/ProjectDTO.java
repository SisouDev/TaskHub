package com.taskhub.taskhub.domain.dto.projectmanagement;

import java.time.LocalDateTime;

public record ProjectDTO(Long id,
                         String name,
                         String description,
                         LocalDateTime creationDate,
                         Long leaderId,
                         Long departmentId) {
}
