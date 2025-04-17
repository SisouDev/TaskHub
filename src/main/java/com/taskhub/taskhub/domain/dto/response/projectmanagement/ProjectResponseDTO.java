package com.taskhub.taskhub.domain.dto.response.projectmanagement;

import java.time.LocalDateTime;

public record ProjectResponseDTO(
        Long id,
        String name,
        String description,
        LocalDateTime creationDate,
        String leaderName,
        String departmentName,
        String projectCategoryName
) {
}
