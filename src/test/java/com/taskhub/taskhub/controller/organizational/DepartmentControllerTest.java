package com.taskhub.taskhub.controller.organizational;

import com.taskhub.taskhub.domain.dto.request.organizational.DepartmentRequestDTO;
import com.taskhub.taskhub.domain.dto.response.organizational.DepartmentResponseDTO;
import com.taskhub.taskhub.domain.service.organizational.DepartmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

    @InjectMocks
    private DepartmentController controller;

    @Mock
    private DepartmentService departmentService;

    @Test
    void testCreateDepartment() {
        DepartmentRequestDTO request = new DepartmentRequestDTO("TI", "Tecnologia da Informação", "url", 1L);
        DepartmentResponseDTO response = new DepartmentResponseDTO(1L, "TI", "Tecnologia da Informação", "url", "Antony");

        when(departmentService.create(request)).thenReturn(response);

        ResponseEntity<DepartmentResponseDTO> result = controller.create(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        assertEquals("TI", result.getBody().name());
    }

    @Test
    void testDeleteDepartment() {
        doNothing().when(departmentService).delete(1L);

        ResponseEntity<Void> result = controller.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(departmentService).delete(1L);
    }
}
