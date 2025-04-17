package com.taskhub.taskhub.domain.dto.response.organizational;

public record DepartmentResponseDTO(
        Long id,
        String name,
        String description,
        String iconUrl,
        String managerName
) {
}
