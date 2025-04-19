package com.taskhub.taskhub.infrastructure.service.organizational;

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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
}
