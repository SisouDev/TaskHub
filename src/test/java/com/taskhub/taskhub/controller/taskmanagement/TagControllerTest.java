package com.taskhub.taskhub.controller.taskmanagement;

import com.taskhub.taskhub.domain.dto.request.taskmanagement.TagRequestDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TagResponseDTO;
import com.taskhub.taskhub.domain.service.taskmanagement.TagService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TagControllerTest {

    @InjectMocks
    private TagController controller;

    @Mock
    private TagService tagService;

    @Test
    void testCreateTag() {
        TagRequestDTO request = new TagRequestDTO("Backend");
        TagResponseDTO response = new TagResponseDTO(1L, "Backend");

        when(tagService.create(request)).thenReturn(response);

        ResponseEntity<TagResponseDTO> result = controller.create(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        assertEquals("Backend", result.getBody().name());
    }

    @Test
    void testGetAllTags() {
        List<TagResponseDTO> list = List.of(new TagResponseDTO(1L, "Backend"));

        when(tagService.getAll()).thenReturn(list);

        ResponseEntity<List<TagResponseDTO>> result = controller.getAll();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
    }

    @Test
    void testDeleteTag() {
        doNothing().when(tagService).delete(1L);

        ResponseEntity<Void> result = controller.delete(1L);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(tagService).delete(1L);
    }
}
