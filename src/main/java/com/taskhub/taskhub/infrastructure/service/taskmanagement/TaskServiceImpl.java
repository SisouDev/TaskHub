package com.taskhub.taskhub.infrastructure.service.taskmanagement;

import com.taskhub.taskhub.domain.dto.request.taskmanagement.TaskRequestDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TaskResponseDTO;
import com.taskhub.taskhub.domain.entities.core.User;
import com.taskhub.taskhub.domain.entities.projectmanagement.Project;
import com.taskhub.taskhub.domain.entities.taskmanagement.Tag;
import com.taskhub.taskhub.domain.entities.taskmanagement.Task;
import com.taskhub.taskhub.domain.enums.Status;
import com.taskhub.taskhub.domain.repository.core.UserRepository;
import com.taskhub.taskhub.domain.repository.projectmanagement.ProjectRepository;
import com.taskhub.taskhub.domain.repository.taskmanagement.TagRepository;
import com.taskhub.taskhub.domain.repository.taskmanagement.TaskRepository;
import com.taskhub.taskhub.domain.service.taskmanagement.TaskService;
import com.taskhub.taskhub.exceptions.project.ProjectNotFoundException;
import com.taskhub.taskhub.exceptions.taskmanagement.TagNotFoundException;
import com.taskhub.taskhub.exceptions.taskmanagement.TaskNotFoundException;
import com.taskhub.taskhub.exceptions.user.UserNotFoundException;
import com.taskhub.taskhub.infrastructure.converter.taskmanagement.TaskConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final Logger logger = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;
    private final TaskConverter taskConverter;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final TagRepository tagRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, TaskConverter taskConverter, UserRepository userRepository, ProjectRepository projectRepository, TagRepository tagRepository) {
        this.taskRepository = taskRepository;
        this.taskConverter = taskConverter;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.tagRepository = tagRepository;
    }

    @Override
    public TaskResponseDTO create(TaskRequestDTO dto) {
        logger.info("Attempting to create a new task with the following details: {}", dto);
        try {
            Task task = taskConverter.toEntity(dto);
            task = taskRepository.save(task);

            logger.info("Task successfully created with ID: {}", task.getId());
            return taskConverter.toResponseDTO(task);
        } catch (Exception e) {
            logger.error("Unexpected error occurred while creating task", e);
            throw new RuntimeException("Unexpected error occurred while creating task", e);
        }
    }

    @Override
    public TaskResponseDTO update(Long id, TaskRequestDTO dto) {
        logger.info("Attempting to update task with ID: {}", id);

        try {
            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + id));

            task.setTitle(dto.title());
            task.setDescription(dto.description());
            task.setDeadline(dto.deadline());

            task = taskRepository.save(task);
            logger.info("Task updated successfully with ID: {}", id);

            return taskConverter.toResponseDTO(task);
        } catch (TaskNotFoundException e) {
            logger.warn("Update failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while updating task with ID: {}", id, e);
            throw new RuntimeException("Unexpected error occurred while updating task", e);
        }
    }

    @Override
    public TaskResponseDTO getById(Long id) {
        logger.info("Attempting to get task with ID: {}", id);

        try {
            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + id));

            return taskConverter.toResponseDTO(task);
        } catch (TaskNotFoundException e) {
            logger.warn("Fetch failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while fetching task with ID: {}", id, e);
            throw new RuntimeException("Unexpected error occurred while fetching task", e);
        }
    }

    @Override
    public List<TaskResponseDTO> getAll() {
        logger.info("Attempting to get all tasks");

        try {
            List<Task> tasks = taskRepository.findAll();
            List<TaskResponseDTO> taskDTOs = tasks.stream()
                    .map(taskConverter::toResponseDTO)
                    .toList();

            logger.info("Retrieved {} tasks", taskDTOs.size());
            return taskDTOs;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while fetching all tasks", e);
            throw new RuntimeException("Unexpected error occurred while fetching all tasks", e);
        }
    }

    @Override
    public void delete(Long id) {
        logger.info("Attempting to delete task with ID: {}", id);

        try {
            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + id));

            taskRepository.delete(task);
            logger.info("Task deleted successfully with ID: {}", id);
        } catch (TaskNotFoundException e) {
            logger.warn("Delete failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while deleting task with ID: {}", id, e);
            throw new RuntimeException("Unexpected error occurred while deleting task", e);
        }
    }


    @Override
    public void assignUserToTask(Long taskId, Long userId) {
        logger.info("Attempting to assign user with ID: {} to task with ID: {}", userId, taskId);

        try {
            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + taskId));

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

            task.getUsers().add(user);
            taskRepository.save(task);

            logger.info("User with ID: {} successfully assigned to task with ID: {}", userId, taskId);
        } catch (TaskNotFoundException | UserNotFoundException e) {
            logger.warn("Assignment failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while assigning user to task", e);
            throw new RuntimeException("Unexpected error occurred while assigning user to task", e);
        }
    }

    @Override
    public void changeStatus(Long taskId, Status newStatus) {
        logger.info("Attempting to change status for task with ID: {}", taskId);

        try {
            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + taskId));

            task.setStatus(newStatus);
            taskRepository.save(task);

            logger.info("Status for task with ID: {} changed to {}", taskId, newStatus);
        } catch (TaskNotFoundException e) {
            logger.warn("Status change failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while changing task status", e);
            throw new RuntimeException("Unexpected error occurred while changing task status", e);
        }
    }

    @Override
    public void removeUserFromTask(Long taskId, Long userId) {
        logger.info("Attempting to remove user with ID: {} from task with ID: {}", userId, taskId);

        try {
            Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new TaskNotFoundException("Task not found with ID: " + taskId));

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

            task.getUsers().remove(user);
            taskRepository.save(task);

            logger.info("User with ID: {} successfully removed from task with ID: {}", userId, taskId);
        } catch (TaskNotFoundException | UserNotFoundException e) {
            logger.warn("Remove user failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while removing user from task", e);
            throw new RuntimeException("Unexpected error occurred while removing user from task", e);
        }
    }

    @Override
    public List<TaskResponseDTO> getTasksByCategory(Long categoryId) {
        logger.info("Fetching tasks for category with ID: {}", categoryId);

        try {
            List<Task> tasks = taskRepository.findByCategoriesId(categoryId);
            List<TaskResponseDTO> taskDTOs = tasks.stream()
                    .map(taskConverter::toResponseDTO)
                    .toList();

            logger.info("Found {} tasks for category with ID: {}", taskDTOs.size(), categoryId);
            return taskDTOs;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while fetching tasks by category", e);
            throw new RuntimeException("Unexpected error occurred while fetching tasks by category", e);
        }
    }

    @Override
    public List<TaskResponseDTO> getTasksByProject(Long projectId) {
        logger.info("Fetching tasks for project with ID: {}", projectId);

        try {
            Project project = projectRepository.findById(projectId)
                    .orElseThrow(() -> new ProjectNotFoundException("Project not found with ID: " + projectId));

            List<Task> tasks = new ArrayList<>(project.getTasks());
            List<TaskResponseDTO> taskDTOs = tasks.stream()
                    .map(taskConverter::toResponseDTO)
                    .toList();

            logger.info("Found {} tasks for project with ID: {}", taskDTOs.size(), projectId);
            return taskDTOs;
        } catch (ProjectNotFoundException e) {
            logger.warn("Fetch failed : {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while fetching tasks by project", e);
            throw new RuntimeException("Unexpected error occurred while fetching tasks by project", e);
        }
    }

    @Override
    public List<TaskResponseDTO> getTasksByTag(Long tagId) {
        logger.info("Fetching tasks for tag with ID: {}", tagId);

        try {
            Tag tag = tagRepository.findById(tagId)
                    .orElseThrow(() -> new TagNotFoundException("Tag not found with ID: " + tagId));

            List<Task> tasks = new ArrayList<>(tag.getTasks());
            List<TaskResponseDTO> taskDTOs = tasks.stream()
                    .map(taskConverter::toResponseDTO)
                    .toList();

            logger.info("Found {} tasks for tag with ID: {}", taskDTOs.size(), tagId);
            return taskDTOs;
        } catch (TagNotFoundException e) {
            logger.warn("Fetch failed! : {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while fetching tasks by tag", e);
            throw new RuntimeException("Unexpected error occurred while fetching tasks by tag", e);
        }
    }
}
