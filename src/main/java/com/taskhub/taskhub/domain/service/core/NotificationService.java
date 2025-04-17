package com.taskhub.taskhub.domain.service.core;

import com.taskhub.taskhub.domain.dto.response.core.NotificationResponseDTO;

import java.util.List;

public interface NotificationService {
    NotificationResponseDTO sendToUser(Long userId, NotificationResponseDTO dto);
    List<NotificationResponseDTO> getUserNotifications(Long userId);
    void markAsRead(Long notificationId);
}
