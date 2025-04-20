package com.taskhub.taskhub.controller.organizational;

import com.taskhub.taskhub.domain.dto.request.organizational.DepartmentRequestDTO;
import com.taskhub.taskhub.domain.dto.response.organizational.DepartmentResponseDTO;
import com.taskhub.taskhub.domain.service.organizational.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> create(@RequestBody @Valid DepartmentRequestDTO dto) {
        DepartmentResponseDTO created = departmentService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> update(@PathVariable Long id, @RequestBody @Valid DepartmentRequestDTO dto) {
        return ResponseEntity.ok(departmentService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(departmentService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAll() {
        return ResponseEntity.ok(departmentService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        departmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}