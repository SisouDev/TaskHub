package com.taskhub.taskhub.infrastructure.service.taskmanagement;

import com.taskhub.taskhub.domain.dto.request.taskmanagement.TagRequestDTO;
import com.taskhub.taskhub.domain.repository.taskmanagement.TagRepository;
import com.taskhub.taskhub.exceptions.taskmanagement.TagAlreadyExistsException;
import com.taskhub.taskhub.exceptions.taskmanagement.TagNotFoundException;
import com.taskhub.taskhub.infrastructure.converter.taskmanagement.TagConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TagServiceImplTest {

    @Mock private TagRepository tagRepository;
    @Mock
    private TagConverter tagConverter;
    @InjectMocks
    private TagServiceImpl service;

    @Test
    void testCreate_shouldThrowIfExists() {
        TagRequestDTO dto = new TagRequestDTO("Backend");
        when(tagRepository.existsByName("Backend")).thenReturn(true);
        assertThrows(TagAlreadyExistsException.class, () -> service.create(dto));
    }

    @Test
    void testDelete_shouldThrowIfNotFound() {
        when(tagRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(TagNotFoundException.class, () -> service.delete(1L));
    }
}

