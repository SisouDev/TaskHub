package com.taskhub.taskhub.controller.core;

import com.taskhub.taskhub.domain.dto.request.core.NotificationRequestDTO;
import com.taskhub.taskhub.domain.dto.response.core.NotificationResponseDTO;
import com.taskhub.taskhub.domain.service.core.NotificationService;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationControllerTest {

    @InjectMocks
    private NotificationController controller;

    @Mock
    private NotificationService notificationService;

    @Test
    void testSendNotification() {
        NotificationRequestDTO request = new NotificationRequestDTO("Nova notificação", false, LocalDateTime.now(), 1L);
        NotificationResponseDTO response = new NotificationResponseDTO(1L, "Nova notificação", false, LocalDateTime.now(), 1L);

        when(notificationService.sendToUser(1L, request)).thenReturn(response);

        ResponseEntity<NotificationResponseDTO> result = controller.sendToUser(1L, request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        assertEquals("Nova notificação", result.getBody().message());
    }

    @Test
    void testMarkAsRead() {
        doNothing().when(notificationService).markAsRead(1L);

        ResponseEntity<Void> result = controller.markAsRead(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(notificationService).markAsRead(1L);
    }
}
