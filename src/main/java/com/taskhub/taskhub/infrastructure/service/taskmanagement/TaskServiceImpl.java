package com.taskhub.taskhub.infrastructure.service.taskmanagement;

import com.taskhub.taskhub.domain.dto.request.taskmanagement.TaskRequestDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TaskResponseDTO;
import com.taskhub.taskhub.domain.enums.Status;
import com.taskhub.taskhub.domain.service.taskmanagement.TaskService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Override
    public TaskRequestDTO create(TaskResponseDTO dto) {
        return null;
    }

    @Override
    public TaskRequestDTO update(Long id, TaskResponseDTO dto) {
        return null;
    }

    @Override
    public TaskResponseDTO getById(Long id) {
        return null;
    }

    @Override
    public List<TaskResponseDTO> getAll() {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void assignUserToTask(Long taskId, Long userId) {

    }

    @Override
    public void changeStatus(Long taskId, Status newStatus) {

    }

    @Override
    public void removeUserFromTask(Long taskId, Long userId) {

    }

    @Override
    public List<TaskResponseDTO> getTasksByCategory(Long categoryId) {
        return List.of();
    }

    @Override
    public List<TaskResponseDTO> getTasksByProject(Long projectId) {
        return List.of();
    }

    @Override
    public List<TaskResponseDTO> getTasksByTag(Long tagId) {
        return List.of();
    }
}
