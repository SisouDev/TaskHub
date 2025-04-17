package com.taskhub.taskhub.domain.dto.request.projectmanagement;

public record ProjectRequestDTO(
        String name,
        String description,
        Long leaderId,
        Long departmentId,
        Long projectCategoryId
) {
}
