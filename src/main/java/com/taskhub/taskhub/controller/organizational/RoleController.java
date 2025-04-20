package com.taskhub.taskhub.controller.organizational;

import com.taskhub.taskhub.domain.dto.response.organizational.RoleResponseDTO;
import com.taskhub.taskhub.domain.service.organizational.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public ResponseEntity<List<RoleResponseDTO>> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.getById(id));
    }
}

