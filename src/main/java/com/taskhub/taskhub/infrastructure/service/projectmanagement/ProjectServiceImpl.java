package com.taskhub.taskhub.infrastructure.service.projectmanagement;

import com.taskhub.taskhub.domain.dto.request.projectmanagement.ProjectRequestDTO;
import com.taskhub.taskhub.domain.dto.response.projectmanagement.ProjectResponseDTO;
import com.taskhub.taskhub.domain.service.projectmanagement.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Override
    public ProjectResponseDTO create(ProjectRequestDTO dto) {
        return null;
    }

    @Override
    public ProjectResponseDTO update(Long id, ProjectRequestDTO dto) {
        return null;
    }

    @Override
    public ProjectResponseDTO getById(Long id) {
        return null;
    }

    @Override
    public List<ProjectResponseDTO> getAll() {
        return List.of();
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void assignUser(Long projectId, Long userId) {

    }

    @Override
    public void removeUser(Long projectId, Long userId) {

    }

    @Override
    public void changeLeader(Long projectId, Long userId) {

    }

    @Override
    public List<ProjectResponseDTO> getProjectMembers(Long projectId) {
        return List.of();
    }
}
