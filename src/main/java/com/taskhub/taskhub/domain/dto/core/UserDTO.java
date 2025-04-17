package com.taskhub.taskhub.domain.dto.core;

import com.taskhub.taskhub.domain.enums.Permission;

public record UserDTO(Long id,
                      String firstName,
                      String lastName,
                      String email,
                      String phoneNumber,
                      String photoUrl,
                      Permission permission,
                      Long roleId,
                      Long departmentId) {
}
