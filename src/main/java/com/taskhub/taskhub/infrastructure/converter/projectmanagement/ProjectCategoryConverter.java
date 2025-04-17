package com.taskhub.taskhub.infrastructure.converter.projectmanagement;

import com.taskhub.taskhub.domain.dto.request.projectmanagement.ProjectCategoryRequestDTO;
import com.taskhub.taskhub.domain.dto.response.projectmanagement.ProjectCategoryResponseDTO;
import com.taskhub.taskhub.domain.entities.projectmanagement.ProjectCategory;
import org.springframework.stereotype.Component;

@Component
public class ProjectCategoryConverter {
    public ProjectCategory toEntity(ProjectCategoryRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        ProjectCategory projectCategory = new ProjectCategory();
        projectCategory.setName(requestDTO.name());

        return projectCategory;
    }

    public ProjectCategoryResponseDTO toResponseDTO(ProjectCategory projectCategory) {
        if (projectCategory == null) {
            return null;
        }

        return new ProjectCategoryResponseDTO(
                projectCategory.getId(),
                projectCategory.getName()
        );
    }
}
