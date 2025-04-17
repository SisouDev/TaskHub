package com.taskhub.taskhub.infrastructure.service.core;

import com.taskhub.taskhub.domain.dto.request.core.UserRequestDTO;
import com.taskhub.taskhub.domain.dto.response.core.UserResponseDTO;
import com.taskhub.taskhub.domain.dto.response.projectmanagement.ProjectResponseDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TaskResponseDTO;
import com.taskhub.taskhub.domain.service.core.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        return null;
    }

    @Override
    public UserResponseDTO updateUser(Long id) {
        return null;
    }

    @Override
    public void deleteUserById(Long id) {

    }

    @Override
    public void changeUserRole(Long userId, Long roleId) {

    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        return null;
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return List.of();
    }

    @Override
    public List<TaskResponseDTO> getUserTasks(Long userId) {
        return List.of();
    }

    @Override
    public List<ProjectResponseDTO> getUserProjects(Long userId) {
        return List.of();
    }
}
