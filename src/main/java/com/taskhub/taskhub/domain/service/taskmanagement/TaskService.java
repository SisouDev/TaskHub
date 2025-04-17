package com.taskhub.taskhub.domain.service.taskmanagement;

import com.taskhub.taskhub.domain.dto.request.taskmanagement.TaskRequestDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TaskResponseDTO;
import com.taskhub.taskhub.domain.enums.Status;

import java.util.List;

public interface TaskService {
    TaskResponseDTO create(TaskRequestDTO dto);
    TaskResponseDTO update(Long id, TaskRequestDTO dto);
    TaskResponseDTO getById(Long id);
    List<TaskResponseDTO> getAll();

    void delete(Long id);
    void assignUserToTask(Long taskId, Long userId);
    void changeStatus(Long taskId, Status newStatus);
    void removeUserFromTask(Long taskId, Long userId);

    List<TaskResponseDTO> getTasksByCategory(Long categoryId);
    List<TaskResponseDTO> getTasksByProject(Long projectId);
    List<TaskResponseDTO> getTasksByTag(Long tagId);

}
