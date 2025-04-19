package com.taskhub.taskhub.infrastructure.service.taskmanagement;

import com.taskhub.taskhub.domain.dto.request.taskmanagement.TagRequestDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TagResponseDTO;
import com.taskhub.taskhub.domain.entities.taskmanagement.Tag;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
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

    @Test
    void testCreate_shouldSaveAndReturnDTO() {
        TagRequestDTO dto = new TagRequestDTO("Frontend");
        Tag tag = new Tag();
        TagResponseDTO response = mock(TagResponseDTO.class);

        when(tagRepository.existsByName("Frontend")).thenReturn(false);
        when(tagConverter.toEntity(dto)).thenReturn(tag);
        when(tagRepository.save(tag)).thenReturn(tag);
        when(tagConverter.toResponseDTO(tag)).thenReturn(response);

        TagResponseDTO result = service.create(dto);
        assertEquals(response, result);
    }

    @Test
    void testUpdate_shouldSaveNewName() {
        TagRequestDTO dto = new TagRequestDTO("UpdatedTag");
        Tag tag = new Tag(); tag.setName("OldTag");
        TagResponseDTO response = mock(TagResponseDTO.class);

        when(tagRepository.findById(1L)).thenReturn(Optional.of(tag));
        when(tagRepository.save(tag)).thenReturn(tag);
        when(tagConverter.toResponseDTO(tag)).thenReturn(response);

        TagResponseDTO result = service.update(1L, dto);
        assertEquals("UpdatedTag", tag.getName());
        assertEquals(response, result);
    }

}

