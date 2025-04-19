package com.taskhub.taskhub.infrastructure.service.core;

import com.taskhub.taskhub.domain.dto.request.core.UserRequestDTO;
import com.taskhub.taskhub.domain.dto.response.core.UserResponseDTO;
import com.taskhub.taskhub.domain.entities.core.User;
import com.taskhub.taskhub.domain.enums.Permission;
import com.taskhub.taskhub.domain.repository.core.UserRepository;
import com.taskhub.taskhub.domain.repository.organizational.RoleRepository;
import com.taskhub.taskhub.exceptions.user.UserAlreadyExistsException;
import com.taskhub.taskhub.exceptions.user.UserNotFoundException;
import com.taskhub.taskhub.infrastructure.converter.core.UserConverter;
import com.taskhub.taskhub.infrastructure.converter.projectmanagement.ProjectConverter;
import com.taskhub.taskhub.infrastructure.converter.taskmanagement.TaskConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock private UserRepository repository;
    @Mock private RoleRepository roleRepository;
    @Mock
    private UserConverter userConverter;
    @Mock private TaskConverter taskConverter;
    @Mock private ProjectConverter projectConverter;
    @InjectMocks
    private UserServiceImpl service;

    @Test
    void testCreateUser_shouldThrowIfEmailExists() {
        UserRequestDTO dto = new UserRequestDTO("A", "B", "email@test.com", "123", null, Permission.USER, 1L, 1L);
        when(repository.existsByEmail(dto.email())).thenReturn(true);
        assertThrows(UserAlreadyExistsException.class, () -> service.createUser(dto));
    }

    @Test
    void testDeleteUser_shouldThrowIfNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> service.deleteUserById(1L));
    }

    @Test
    void testUpdateUser_shouldUpdateBasicFields() {
        User existing = new User();
        existing.setId(1L);
        existing.setFirstName("Old");
        existing.setLastName("Name");
        existing.setPhoneNumber("0000");

        UserRequestDTO dto = new UserRequestDTO("New", "Name", "email@test.com", "1234", null, Permission.USER, 1L, 1L);

        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any())).thenReturn(existing);
        when(userConverter.toResponseDTO(any())).thenReturn(mock(UserResponseDTO.class));

        UserResponseDTO result = service.updateUser(1L, dto);
        verify(repository).save(existing);
        assertNotNull(result);
    }

    @Test
    void testGetAllUsers_shouldReturnEmptyList() {
        when(repository.findAll()).thenReturn(List.of());

        List<UserResponseDTO> users = service.getAllUsers();
        assertTrue(users.isEmpty());
    }

}

