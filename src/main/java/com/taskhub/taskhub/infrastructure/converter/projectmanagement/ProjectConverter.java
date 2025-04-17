package com.taskhub.taskhub.infrastructure.converter.projectmanagement;

import com.taskhub.taskhub.domain.dto.request.projectmanagement.ProjectRequestDTO;
import com.taskhub.taskhub.domain.dto.response.projectmanagement.ProjectResponseDTO;
import com.taskhub.taskhub.domain.entities.core.User;
import com.taskhub.taskhub.domain.entities.organizational.Department;
import com.taskhub.taskhub.domain.entities.projectmanagement.Project;
import com.taskhub.taskhub.domain.entities.projectmanagement.ProjectCategory;
import com.taskhub.taskhub.domain.repository.core.UserRepository;
import com.taskhub.taskhub.domain.repository.organizational.DepartmentRepository;
import com.taskhub.taskhub.domain.repository.projectmanagement.ProjectCategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final ProjectCategoryRepository projectCategoryRepository;

    public ProjectConverter(UserRepository userRepository,
                            DepartmentRepository departmentRepository,
                            ProjectCategoryRepository projectCategoryRepository) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.projectCategoryRepository = projectCategoryRepository;
    }

    // Conversão de ProjectRequestDTO para Project
    public Project toEntity(ProjectRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        Project project = new Project();
        project.setName(requestDTO.name());
        project.setDescription(requestDTO.description());

        User leader = userRepository.findById(requestDTO.leaderId()).orElse(null);
        project.setLeader(leader);

        Department department = departmentRepository.findById(requestDTO.departmentId()).orElse(null);
        project.setDepartment(department);

        ProjectCategory projectCategory = projectCategoryRepository.findById(requestDTO.projectCategoryId()).orElse(null);
        project.setProjectCategory(projectCategory);

        return project;
    }

    public ProjectResponseDTO toResponseDTO(Project project) {
        if (project == null) {
            return null;
        }

        String leaderName = project.getLeader() != null ? project.getLeader().getFirstName() + " " + project.getLeader().getLastName() : null;
        String departmentName = project.getDepartment() != null ? project.getDepartment().getName() : null;
        String projectCategoryName = project.getProjectCategory() != null ? project.getProjectCategory().getName() : null;

        return new ProjectResponseDTO(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getCreationDate(),
                leaderName,
                departmentName,
                projectCategoryName
        );
    }
}
