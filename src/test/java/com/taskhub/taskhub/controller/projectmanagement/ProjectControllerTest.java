package com.taskhub.taskhub.controller.projectmanagement;

import com.taskhub.taskhub.domain.dto.request.projectmanagement.ProjectRequestDTO;
import com.taskhub.taskhub.domain.dto.response.projectmanagement.ProjectResponseDTO;
import com.taskhub.taskhub.domain.service.projectmanagement.ProjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    @InjectMocks
    private ProjectController controller;

    @Mock
    private ProjectService projectService;

    @Test
    void testCreateProject() {
        ProjectRequestDTO request = new ProjectRequestDTO("Projeto X", "Descrição", 1L, 1L, 1L);
        ProjectResponseDTO response = new ProjectResponseDTO(1L, "Projeto X", "Descrição", LocalDateTime.now(), "Maria", "TI", "Categoria");

        when(projectService.create(request)).thenReturn(response);

        ResponseEntity<ProjectResponseDTO> result = controller.createProject(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        assertEquals("Projeto X", result.getBody().name());
    }

    @Test
    void testGetProjectById() {
        ProjectResponseDTO dto = new ProjectResponseDTO(1L, "Projeto X", "Descrição", LocalDateTime.now(), "Maria", "TI", "Categoria");

        when(projectService.getById(1L)).thenReturn(dto);

        ResponseEntity<ProjectResponseDTO> result = controller.getById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        assertEquals("Projeto X", result.getBody().name());
    }
}
