package com.taskhub.taskhub.infrastructure.service.core;

import com.taskhub.taskhub.domain.dto.response.core.NotificationResponseDTO;
import com.taskhub.taskhub.domain.service.core.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {
    @Override
    public NotificationResponseDTO sendToUser(Long userId, NotificationResponseDTO dto) {
        return null;
    }

    @Override
    public List<NotificationResponseDTO> getUserNotifications(Long userId) {
        return List.of();
    }

    @Override
    public void markAsRead(Long notificationId) {

    }
}
