package com.taskhub.taskhub.infrastructure.service.core;

import com.taskhub.taskhub.domain.dto.request.core.UserRequestDTO;
import com.taskhub.taskhub.domain.dto.response.core.UserResponseDTO;
import com.taskhub.taskhub.domain.dto.response.projectmanagement.ProjectResponseDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TaskResponseDTO;
import com.taskhub.taskhub.domain.entities.core.User;
import com.taskhub.taskhub.domain.entities.organizational.Role;
import com.taskhub.taskhub.domain.entities.projectmanagement.Project;
import com.taskhub.taskhub.domain.entities.taskmanagement.Task;
import com.taskhub.taskhub.domain.repository.core.UserRepository;
import com.taskhub.taskhub.domain.repository.organizational.RoleRepository;
import com.taskhub.taskhub.domain.service.core.UserService;
import com.taskhub.taskhub.exceptions.organizational.RoleNotFoundException;
import com.taskhub.taskhub.exceptions.user.UserAlreadyExistsException;
import com.taskhub.taskhub.exceptions.user.UserNotFoundException;
import com.taskhub.taskhub.infrastructure.converter.core.UserConverter;
import com.taskhub.taskhub.infrastructure.converter.projectmanagement.ProjectConverter;
import com.taskhub.taskhub.infrastructure.converter.taskmanagement.TaskConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final TaskConverter taskConverter;
    private final ProjectConverter projectConverter;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter, TaskConverter taskConverter, ProjectConverter projectConverter, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.taskConverter = taskConverter;
        this.projectConverter = projectConverter;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        logger.info("Attempting to create a new user with the following details: {}", userRequestDTO);

        try {
            if (userRepository.existsByEmail(userRequestDTO.email())) {
                logger.warn("User creation failed: Email already exists: {}", userRequestDTO.email());
                throw new UserAlreadyExistsException("Email already exists");
            }
            User user = userConverter.toEntity(userRequestDTO);
            user = userRepository.save(user);

            logger.info("User successfully created with ID: {}", user.getId());

            return userConverter.toResponseDTO(user);
        } catch (UserAlreadyExistsException e) {
            logger.error("User creation failed due to existing email: {}", userRequestDTO.email(), e);
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while creating user", e);
            throw new RuntimeException("Unexpected error occurred", e);
        }
    }

    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) {
        logger.info("Attempting to update user with ID: {}", id);

        try {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

            user.setFirstName(userRequestDTO.firstName());
            user.setLastName(userRequestDTO.lastName());
            user.setPhoneNumber(String.valueOf(userRequestDTO.phoneNumber()));
            user.setPhotoUrl(userRequestDTO.photoUrl());
            user = userRepository.save(user);
            logger.info("User updated successfully with ID: {}", id);

            return userConverter.toResponseDTO(user);
        } catch (UserNotFoundException e) {
            logger.warn("Update failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while updating user with ID: {}", id, e);
            throw new RuntimeException("Unexpected error occurred while updating user", e);
        }
    }

    @Override
    public void deleteUserById(Long id) {
        logger.info("Attempting to delete user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        userRepository.delete(user);
        logger.info("User deleted successfully with ID: {}", id);
    }

    @Override
    public void changeUserRole(Long userId, Long roleId) {
        logger.info("Attempting to change user role. User ID: {}, Role ID: {}", userId, roleId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() -> new RoleNotFoundException("Role not found with ID: " + roleId));

        user.setRole(role);
        userRepository.save(user);
        logger.info("User role successfully changed. User ID: {}, New Role ID: {}", userId, roleId);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        logger.info("Attempting to get user with ID: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));
        return userConverter.toResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        logger.info("Attempting to get all users");

        List<User> users = userRepository.findAll();
        List<UserResponseDTO> userDTOs = users.stream()
                .map(userConverter::toResponseDTO)
                .toList();

        logger.info("Retrieved {} users", userDTOs.size());
        return userDTOs;
    }


    @Override
    public List<TaskResponseDTO> getUserTasks(Long userId) {
        logger.info("Fetching tasks for user with ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        List<Task> tasks = new ArrayList<>(user.getTasks());
        List<TaskResponseDTO> taskDTOs = tasks.stream()
                .map(taskConverter::toResponseDTO)
                .toList();

        logger.info("Found {} tasks for user with ID: {}", taskDTOs.size(), userId);
        return taskDTOs;
    }

    @Override
    public List<ProjectResponseDTO> getUserProjects(Long userId) {
        logger.info("Fetching projects for user with ID: {}", userId);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        List<Project> projects = new ArrayList<>(user.getProjects());
        List<ProjectResponseDTO> projectDTOs = projects.stream()
                .map(projectConverter::toResponseDTO)
                .toList();

        logger.info("Found {} projects for user with ID: {}", projectDTOs.size(), userId);
        return projectDTOs;
    }
}
