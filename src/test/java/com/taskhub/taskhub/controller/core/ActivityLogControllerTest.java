package com.taskhub.taskhub.controller.core;

import com.taskhub.taskhub.domain.dto.response.core.ActivityLogResponseDTO;
import com.taskhub.taskhub.domain.service.core.ActivityLogService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ActivityLogControllerTest {

    @InjectMocks
    private ActivityLogController controller;

    @Mock
    private ActivityLogService activityLogService;

    @Test
    void testGetLogsByUser() {
        List<ActivityLogResponseDTO> logs = List.of(
                new ActivityLogResponseDTO(1L, "Create", LocalDateTime.now(), "User", 2L, 3L)
        );

        when(activityLogService.getLogsByUser(1L)).thenReturn(logs);

        ResponseEntity<List<ActivityLogResponseDTO>> result = controller.getLogsByUser(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        assertEquals(1, result.getBody().size());
        assertEquals("CREATE", result.getBody().getFirst().activity());
    }

    @Test
    void testGetLogsByEntity() {
        List<ActivityLogResponseDTO> logs = List.of(
                new ActivityLogResponseDTO(1L, "Create", LocalDateTime.now(), "User", 2L, 3L)
        );

        when(activityLogService.getLogsByEntity("Project", 5L)).thenReturn(logs);

        ResponseEntity<List<ActivityLogResponseDTO>> result = controller.getLogsByEntity("Project", 5L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        assertEquals("Project", result.getBody().getFirst().entity());
    }
}