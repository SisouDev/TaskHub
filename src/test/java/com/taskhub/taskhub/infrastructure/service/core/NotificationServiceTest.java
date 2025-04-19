package com.taskhub.taskhub.infrastructure.service.core;

import com.taskhub.taskhub.domain.dto.request.core.NotificationRequestDTO;
import com.taskhub.taskhub.domain.dto.response.core.NotificationResponseDTO;
import com.taskhub.taskhub.domain.entities.core.Notification;
import com.taskhub.taskhub.domain.repository.core.NotificationRepository;
import com.taskhub.taskhub.exceptions.core.NotificationNotFoundException;
import com.taskhub.taskhub.infrastructure.converter.core.NotificationConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    @Mock
    private NotificationRepository repository;
    @Mock private NotificationConverter converter;
    @InjectMocks
    private NotificationServiceImpl service;

    @Test
    void testSendToUser_shouldSaveNotification() {
        NotificationRequestDTO dto = new NotificationRequestDTO("Hi", false, LocalDateTime.now(), 1L);
        Notification notification = new Notification();
        notification.setId(1L);

        NotificationResponseDTO response = new NotificationResponseDTO(
                1L, dto.message(), dto.read(), dto.sendDate(), dto.recipientId()
        );

        when(converter.toEntity(dto)).thenReturn(notification);
        when(repository.save(any())).thenReturn(notification);
        when(converter.toResponseDTO(notification)).thenReturn(response);

        NotificationResponseDTO result = service.sendToUser(1L, dto);
        assertNotNull(result);
        assertEquals(response, result);
    }

    @Test
    void testMarkAsRead_shouldThrowIfNotFound() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThrows(NotificationNotFoundException.class, () -> service.markAsRead(99L));
    }
}
