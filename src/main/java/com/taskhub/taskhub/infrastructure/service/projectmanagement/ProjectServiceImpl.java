package com.taskhub.taskhub.infrastructure.service.projectmanagement;

import com.taskhub.taskhub.domain.dto.request.projectmanagement.ProjectRequestDTO;
import com.taskhub.taskhub.domain.dto.response.projectmanagement.ProjectResponseDTO;
import com.taskhub.taskhub.domain.entities.core.User;
import com.taskhub.taskhub.domain.entities.projectmanagement.Project;
import com.taskhub.taskhub.domain.repository.core.UserRepository;
import com.taskhub.taskhub.domain.repository.projectmanagement.ProjectRepository;
import com.taskhub.taskhub.domain.service.projectmanagement.ProjectService;
import com.taskhub.taskhub.exceptions.project.ProjectNotFoundException;
import com.taskhub.taskhub.exceptions.user.UserNotFoundException;
import com.taskhub.taskhub.infrastructure.converter.projectmanagement.ProjectConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final Logger logger = LoggerFactory.getLogger(ProjectServiceImpl.class);
    private final ProjectRepository projectRepository;
    private final ProjectConverter projectConverter;
    private final UserRepository userRepository;

    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, ProjectConverter projectConverter, UserRepository userRepository) {
        this.projectRepository = projectRepository;
        this.projectConverter = projectConverter;
        this.userRepository = userRepository;
    }

    @Override
    public ProjectResponseDTO create(ProjectRequestDTO dto) {
        logger.info("Attempting to create a new project with the following details: {}", dto);

        try {
            Project project = projectConverter.toEntity(dto);
            project = projectRepository.save(project);
            logger.info("Project successfully created with ID: {}", project.getId());
            return projectConverter.toResponseDTO(project);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while creating project", e);
            throw new RuntimeException("Unexpected error occurred", e);
        }
    }

    @Override
    public ProjectResponseDTO update(Long id, ProjectRequestDTO dto) {
        logger.info("Attempting to update project with ID: {}", id);

        try {
            Project project = projectRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + id));

            project.setName(dto.name());
            project.setDescription(dto.description());
            project = projectRepository.save(project);

            logger.info("Project successfully updated with ID: {}", id);
            return projectConverter.toResponseDTO(project);
        } catch (ProjectNotFoundException e) {
            logger.warn("Update failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while updating project with ID: {}", id, e);
            throw new RuntimeException("Unexpected error occurred while updating project", e);
        }
    }

    @Override
    public ProjectResponseDTO getById(Long id) {
        logger.info("Attempting to get project with ID: {}", id);

        try {
            Project project = projectRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + id));
            return projectConverter.toResponseDTO(project);
        } catch (ProjectNotFoundException e) {
            logger.warn("Fetch failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error while fetching project with ID: {}", id, e);
            throw new RuntimeException("Unexpected error occurred while fetching project", e);
        }
    }

    @Override
    public List<ProjectResponseDTO> getAll() {
        logger.info("Attempting to get all projects");

        List<Project> projects = projectRepository.findAll();
        List<ProjectResponseDTO> projectDTOs = projects.stream()
                .map(projectConverter::toResponseDTO)
                .toList();

        logger.info("Retrieved {} projects", projectDTOs.size());
        return projectDTOs;
    }

    @Override
    public void delete(Long id) {
        logger.info("Attempting to delete project with ID: {}", id);

        try {
            Project project = projectRepository.findById(id)
                    .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + id));

            projectRepository.delete(project);
            logger.info("Project successfully deleted with ID: {}", id);
        } catch (ProjectNotFoundException e) {
            logger.warn("Delete failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while deleting project with ID: {}", id, e);
            throw new RuntimeException("Unexpected error occurred while deleting project", e);
        }
    }

    @Override
    public void assignUser(Long projectId, Long userId) {
        logger.info("Attempting to assign user with ID: {} to project with ID: {}", userId, projectId);

        try {
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + projectId));

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

            project.getUsers().add(user);  // Assuming Project has a list of users
            projectRepository.save(project);

            logger.info("User with ID: {} successfully assigned to project with ID: {}", userId, projectId);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while assigning user to project", e);
            throw new UserNotFoundException("Unexpected error occurred while assigning user to project", e);
        }
    }

    @Override
    public void removeUser(Long projectId, Long userId) {
        logger.info("Attempting to remove user with ID: {} from project with ID: {}", userId, projectId);

        try {
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + projectId));

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

            project.getUsers().remove(user);  // Assuming Project has a list of users
            projectRepository.save(project);

            logger.info("User with ID: {} successfully removed from project with ID: {}", userId, projectId);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while removing user from project", e);
            throw new RuntimeException("Unexpected error occurred while removing user from project", e);
        }
    }

    @Override
    public void changeLeader(Long projectId, Long userId) {
        logger.info("Attempting to change project leader. Project ID: {}, New Leader User ID: {}", projectId, userId);

        try {
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + projectId));

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

            project.setLeader(user);  // Assuming Project has a leader attribute
            projectRepository.save(project);

            logger.info("Project leader successfully changed. Project ID: {}, New Leader User ID: {}", projectId, userId);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while changing project leader", e);
            throw new UserNotFoundException("Unexpected error occurred while changing project leader", e);
        }
    }

    @Override
    public List<ProjectResponseDTO> getProjectMembers(Long projectId) {
        logger.info("Attempting to fetch project members for project with ID: {}", projectId);

        try {
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + projectId));

            List<User> users = new ArrayList<>(project.getUsers());
            return users.stream()
                    .map(user -> new ProjectResponseDTO(
                            project.getId(),
                            project.getName(),
                            project.getDescription(),
                            project.getCreationDate(),
                            user.getFirstName() + " " + user.getLastName(),
                            project.getDepartment().getName(),
                            project.getProjectCategory().getName()
                    ))
                    .toList();
        } catch (ProjectNotFoundException e) {
            logger.warn("Fetch members failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while fetching project members", e);
            throw new RuntimeException("Unexpected error occurred while fetching project members", e);
        }
    }
}
