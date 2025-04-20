package com.taskhub.taskhub.controller.projectmanagement;

import com.taskhub.taskhub.domain.dto.request.projectmanagement.ProjectRequestDTO;
import com.taskhub.taskhub.domain.dto.response.projectmanagement.ProjectResponseDTO;
import com.taskhub.taskhub.domain.service.projectmanagement.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody @Valid ProjectRequestDTO dto) {
        ProjectResponseDTO created = projectService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> updateProject(@PathVariable Long id, @RequestBody @Valid ProjectRequestDTO dto) {
        return ResponseEntity.ok(projectService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(projectService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponseDTO>> getAll() {
        return ResponseEntity.ok(projectService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{projectId}/assign-user/{userId}")
    public ResponseEntity<Void> assignUser(@PathVariable Long projectId, @PathVariable Long userId) {
        projectService.assignUser(projectId, userId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{projectId}/remove-user/{userId}")
    public ResponseEntity<Void> removeUser(@PathVariable Long projectId, @PathVariable Long userId) {
        projectService.removeUser(projectId, userId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{projectId}/change-leader/{userId}")
    public ResponseEntity<Void> changeLeader(@PathVariable Long projectId, @PathVariable Long userId) {
        projectService.changeLeader(projectId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{projectId}/members")
    public ResponseEntity<List<ProjectResponseDTO>> getMembers(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectService.getProjectMembers(projectId));
    }
}
