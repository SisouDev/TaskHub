package com.taskhub.taskhub.infrastructure.service.core;

import com.taskhub.taskhub.domain.dto.request.core.NotificationRequestDTO;
import com.taskhub.taskhub.domain.dto.response.core.NotificationResponseDTO;
import com.taskhub.taskhub.domain.entities.core.Notification;
import com.taskhub.taskhub.domain.entities.core.User;
import com.taskhub.taskhub.domain.repository.core.NotificationRepository;
import com.taskhub.taskhub.exceptions.core.NotificationNotFoundException;
import com.taskhub.taskhub.infrastructure.converter.core.NotificationConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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

    @Test
    void testGetUserNotifications_shouldReturnNotifications() {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setMessage("Hello");
        notification.setRead(false);
        notification.setSendDate(LocalDateTime.now());
        notification.setRecipient(new User());

        NotificationResponseDTO dto = new NotificationResponseDTO(1L, "Hello", false, notification.getSendDate(), 1L);

        when(repository.findByRecipientId(1L)).thenReturn(List.of(notification));
        when(converter.toResponseDTO(notification)).thenReturn(dto);

        List<NotificationResponseDTO> result = service.getUserNotifications(1L);

        assertEquals(1, result.size());
        assertEquals(dto, result.getFirst());
    }

    @Test
    void testMarkAsRead_shouldUpdateNotification() {
        Notification notification = new Notification();
        notification.setId(1L);
        notification.setRead(false);

        when(repository.findById(1L)).thenReturn(Optional.of(notification));

        service.markAsRead(1L);

        verify(repository).save(notification);
        assertTrue(notification.getRead());
    }
}
