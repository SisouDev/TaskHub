package com.taskhub.taskhub.controller.taskmanagement;

import com.taskhub.taskhub.domain.dto.request.taskmanagement.TaskRequestDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TaskResponseDTO;
import com.taskhub.taskhub.domain.enums.Status;
import com.taskhub.taskhub.domain.service.taskmanagement.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(@RequestBody @Valid TaskRequestDTO dto) {
        TaskResponseDTO created = taskService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> updateTask(@PathVariable Long id, @RequestBody @Valid TaskRequestDTO dto) {
        return ResponseEntity.ok(taskService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAll() {
        return ResponseEntity.ok(taskService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{taskId}/assign-user/{userId}")
    public ResponseEntity<Void> assignUserToTask(@PathVariable Long taskId, @PathVariable Long userId) {
        taskService.assignUserToTask(taskId, userId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{taskId}/remove-user/{userId}")
    public ResponseEntity<Void> removeUserFromTask(@PathVariable Long taskId, @PathVariable Long userId) {
        taskService.removeUserFromTask(taskId, userId);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{taskId}/status/{status}")
    public ResponseEntity<Void> changeStatus(@PathVariable Long taskId, @PathVariable Status status) {
        taskService.changeStatus(taskId, status);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-project/{projectId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByProject(@PathVariable Long projectId) {
        return ResponseEntity.ok(taskService.getTasksByProject(projectId));
    }

    @GetMapping("/by-category/{categoryId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByCategory(@PathVariable Long categoryId) {
        return ResponseEntity.ok(taskService.getTasksByCategory(categoryId));
    }

    @GetMapping("/by-tag/{tagId}")
    public ResponseEntity<List<TaskResponseDTO>> getTasksByTag(@PathVariable Long tagId) {
        return ResponseEntity.ok(taskService.getTasksByTag(tagId));
    }
}
