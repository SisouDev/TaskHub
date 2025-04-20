package com.taskhub.taskhub.controller.taskmanagement;

import com.taskhub.taskhub.domain.dto.request.taskmanagement.TagRequestDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TagResponseDTO;
import com.taskhub.taskhub.domain.service.taskmanagement.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @PostMapping
    public ResponseEntity<TagResponseDTO> create(@RequestBody @Valid TagRequestDTO dto) {
        TagResponseDTO created = tagService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TagResponseDTO> update(@PathVariable Long id, @RequestBody @Valid TagRequestDTO dto) {
        return ResponseEntity.ok(tagService.update(id, dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TagResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(tagService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<TagResponseDTO>> getAll() {
        return ResponseEntity.ok(tagService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        tagService.delete(id);
        return ResponseEntity.noContent().build();
    }
}