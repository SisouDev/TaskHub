package com.taskhub.taskhub.domain.service.core;


import com.taskhub.taskhub.domain.dto.request.core.UserRequestDTO;
import com.taskhub.taskhub.domain.dto.response.core.UserResponseDTO;
import com.taskhub.taskhub.domain.dto.response.projectmanagement.ProjectResponseDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TaskResponseDTO;
import com.taskhub.taskhub.domain.entities.core.User;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    UserResponseDTO updateUser(Long id);

    void deleteUserById(Long id);
    void changeUserRole(Long userId, Long roleId);

    UserResponseDTO getUserById(Long id);
    List<UserResponseDTO> getAllUsers();

    List<TaskResponseDTO> getUserTasks(Long userId);
    List<ProjectResponseDTO> getUserProjects(Long userId);

}
