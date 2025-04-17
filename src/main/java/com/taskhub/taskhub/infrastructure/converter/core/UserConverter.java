package com.taskhub.taskhub.infrastructure.converter.core;

import com.taskhub.taskhub.domain.dto.request.core.UserRequestDTO;
import com.taskhub.taskhub.domain.dto.response.core.UserResponseDTO;
import com.taskhub.taskhub.domain.entities.core.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User toEntity(UserRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }
        User user = new User();
        user.setFirstName(requestDTO.firstName());
        user.setLastName(requestDTO.lastName());
        user.setEmail(requestDTO.email());
        user.setPhoneNumber(requestDTO.phoneNumber());
        user.setPhotoUrl(requestDTO.photoUrl());
        user.setPermission(requestDTO.permission());
        return user;
    }

    public UserResponseDTO toResponseDTO(User user) {
        if (user == null) {
            return null;
        }

        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getPhotoUrl(),
                user.getPermission(),
                user.getRole().getName(),
                user.getDepartment().getName()
        );
    }

}
