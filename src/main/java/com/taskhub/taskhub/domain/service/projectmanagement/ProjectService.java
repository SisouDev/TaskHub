package com.taskhub.taskhub.domain.service.projectmanagement;

import com.taskhub.taskhub.domain.dto.request.projectmanagement.ProjectRequestDTO;
import com.taskhub.taskhub.domain.dto.response.projectmanagement.ProjectResponseDTO;

import java.util.List;

public interface ProjectService {
    ProjectResponseDTO create(ProjectRequestDTO dto);
    ProjectResponseDTO update(Long id, ProjectRequestDTO dto);
    ProjectResponseDTO getById(Long id);
    List<ProjectResponseDTO> getAll();

    void delete(Long id);
    void assignUser(Long projectId, Long userId);
    void removeUser(Long projectId, Long userId);
    void changeLeader(Long projectId, Long userId);

    List<ProjectResponseDTO> getProjectMembers(Long projectId);
}
