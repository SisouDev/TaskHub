package com.taskhub.taskhub.infrastructure.converter.taskmanagement;

import com.taskhub.taskhub.domain.dto.request.taskmanagement.TaskCategoryRequestDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TaskCategoryResponseDTO;
import com.taskhub.taskhub.domain.entities.taskmanagement.TaskCategory;
import org.springframework.stereotype.Component;

@Component
public class TaskCategoryConverter {
    public TaskCategory toEntity(TaskCategoryRequestDTO requestDTO) {
        if (requestDTO == null) return null;
        TaskCategory category = new TaskCategory();
        category.setName(requestDTO.name());
        return category;
    }

    public TaskCategoryResponseDTO toResponseDTO(TaskCategory category) {
        if (category == null) return null;
        return new TaskCategoryResponseDTO(category.getId(), category.getName());
    }
}
