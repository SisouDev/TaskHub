package com.taskhub.taskhub.domain.dto.request.organizational;

public record DepartmentRequestDTO(
        String name,
        String description,
        String iconUrl,
        Long managerId
) {
}
