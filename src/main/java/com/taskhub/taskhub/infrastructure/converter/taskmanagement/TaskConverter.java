package com.taskhub.taskhub.infrastructure.converter.taskmanagement;

import com.taskhub.taskhub.domain.dto.request.taskmanagement.TaskRequestDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TaskResponseDTO;
import com.taskhub.taskhub.domain.entities.taskmanagement.Tag;
import com.taskhub.taskhub.domain.entities.taskmanagement.Task;
import com.taskhub.taskhub.domain.entities.taskmanagement.TaskCategory;
import com.taskhub.taskhub.domain.repository.core.UserRepository;
import com.taskhub.taskhub.domain.repository.projectmanagement.ProjectRepository;
import com.taskhub.taskhub.domain.repository.taskmanagement.TagRepository;
import com.taskhub.taskhub.domain.repository.taskmanagement.TaskCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskConverter {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;
    private final TaskCategoryRepository taskCategoryRepository;

    public Task toEntity(TaskRequestDTO requestDTO) {
        if (requestDTO == null) return null;

        Task task = new Task();
        task.setTitle(requestDTO.title());
        task.setDescription(requestDTO.description());
        task.setDeadline(requestDTO.deadline());
        task.setStatus(requestDTO.status());
        task.setCreatedAt(LocalDateTime.now());

        task.setProject(projectRepository.findById(requestDTO.projectId()).orElseThrow());

        task.setUsers(
                requestDTO.userIds().stream()
                        .map(id -> userRepository.findById(id).orElseThrow())
                        .collect(Collectors.toSet())
        );

        task.setTags(
                requestDTO.tagNames().stream()
                        .map(name -> tagRepository.findByName(name).orElseThrow())
                        .collect(Collectors.toSet())
        );

        task.setCategories(
                requestDTO.categoryNames().stream()
                        .map(name -> taskCategoryRepository.findByName(name).orElseThrow())
                        .collect(Collectors.toSet())
        );

        return task;
    }

    public TaskResponseDTO toResponseDTO(Task task) {
        if (task == null) return null;

        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getCreatedAt(),
                task.getDeadline(),
                task.getStatus(),
                task.getProject().getName(),
                task.getTags().stream().map(Tag::getName).collect(Collectors.toSet()),
                task.getCategories().stream().map(TaskCategory::getName).collect(Collectors.toSet()),
                task.getUsers().stream().map(user -> user.getFirstName() + " " + user.getLastName()).collect(Collectors.toSet())
        );
    }
}
