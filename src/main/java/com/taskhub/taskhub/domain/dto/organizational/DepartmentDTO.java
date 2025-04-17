package com.taskhub.taskhub.domain.dto.organizational;


public record DepartmentDTO(
        Long id,
        String name,
        String description,
        String iconUrl,
        Long managerId
) {
}
