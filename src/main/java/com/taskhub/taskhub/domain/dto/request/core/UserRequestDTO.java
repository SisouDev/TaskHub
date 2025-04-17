package com.taskhub.taskhub.domain.dto.request.core;

import com.taskhub.taskhub.domain.enums.Permission;

public record UserRequestDTO(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String photoUrl,
        Permission permission,
        Long roleId,
        Long departmentId
) {
}
