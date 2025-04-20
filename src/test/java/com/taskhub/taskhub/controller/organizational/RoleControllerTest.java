package com.taskhub.taskhub.controller.organizational;

import com.taskhub.taskhub.domain.dto.response.organizational.RoleResponseDTO;
import com.taskhub.taskhub.domain.service.organizational.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleControllerTest {

    @InjectMocks
    private RoleController controller;

    @Mock
    private RoleService roleService;

    @Test
    void testGetAllRoles() {
        List<RoleResponseDTO> roles = List.of(new RoleResponseDTO(1L, "Dev", "Ti"));

        when(roleService.getAll()).thenReturn(roles);

        ResponseEntity<List<RoleResponseDTO>> result = controller.getAll();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
        assertEquals("Dev", result.getBody().getFirst().name());
    }

    @Test
    void testGetRoleById() {
        RoleResponseDTO dto = new RoleResponseDTO(1L, "Gerente", "Ti");

        when(roleService.getById(1L)).thenReturn(dto);

        ResponseEntity<RoleResponseDTO> result = controller.getById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        assertEquals("Gerente", result.getBody().name());
    }
}
