package com.taskhub.taskhub.infrastructure.service.organizational;

import com.taskhub.taskhub.domain.dto.request.organizational.DepartmentRequestDTO;
import com.taskhub.taskhub.domain.dto.response.organizational.DepartmentResponseDTO;
import com.taskhub.taskhub.domain.entities.organizational.Department;
import com.taskhub.taskhub.domain.repository.organizational.DepartmentRepository;
import com.taskhub.taskhub.exceptions.organizational.DepartmentNotFoundException;
import com.taskhub.taskhub.infrastructure.converter.organizational.DepartmentConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository repository;
    @Mock private DepartmentConverter converter;
    @InjectMocks
    private DepartmentServiceImpl service;

    @Test
    void testGetById_shouldThrowIfNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(DepartmentNotFoundException.class, () -> service.getById(1L));
    }

    @Test
    void testDelete_shouldRemoveIfFound() {
        Department department = new Department();
        when(repository.findById(1L)).thenReturn(Optional.of(department));
        service.delete(1L);
        verify(repository).delete(department);
    }

    @Test
    void testCreate_shouldReturnDTO() {
        DepartmentRequestDTO dto = new DepartmentRequestDTO("TI", "Tech Dept", "icon.png", 1L);
        Department department = new Department();
        DepartmentResponseDTO response = mock(DepartmentResponseDTO.class);

        when(converter.toEntity(dto)).thenReturn(department);
        when(repository.save(department)).thenReturn(department);
        when(converter.toResponseDTO(department)).thenReturn(response);

        DepartmentResponseDTO result = service.create(dto);
        assertEquals(response, result);
    }

    @Test
    void testGetById_shouldReturnDTO() {
        Department department = new Department();
        DepartmentResponseDTO response = mock(DepartmentResponseDTO.class);

        when(repository.findById(1L)).thenReturn(Optional.of(department));
        when(converter.toResponseDTO(department)).thenReturn(response);

        DepartmentResponseDTO result = service.getById(1L);
        assertEquals(response, result);
    }

}
