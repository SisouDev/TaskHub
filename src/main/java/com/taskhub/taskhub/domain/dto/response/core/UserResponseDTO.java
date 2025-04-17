package com.taskhub.taskhub.domain.dto.response.core;

import com.taskhub.taskhub.domain.enums.Permission;

public record UserResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String photoUrl,
        Permission permission,
        String roleName,
        String departmentName
) {
}
