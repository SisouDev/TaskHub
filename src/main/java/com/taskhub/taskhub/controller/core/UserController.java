package com.taskhub.taskhub.controller.core;

import com.taskhub.taskhub.domain.dto.request.core.UserRequestDTO;
import com.taskhub.taskhub.domain.dto.response.core.UserResponseDTO;
import com.taskhub.taskhub.domain.dto.response.projectmanagement.ProjectResponseDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TaskResponseDTO;
import com.taskhub.taskhub.domain.service.core.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO dto) {
        UserResponseDTO created = userService.createUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long id, @RequestBody @Valid UserRequestDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/role/{roleId}")
    public ResponseEntity<Void> changeUserRole(@PathVariable Long id, @PathVariable Long roleId) {
        userService.changeUserRole(id, roleId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/tasks")
    public ResponseEntity<List<TaskResponseDTO>> getUserTasks(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserTasks(id));
    }

    @GetMapping("/{id}/projects")
    public ResponseEntity<List<ProjectResponseDTO>> getUserProjects(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserProjects(id));
    }
}
