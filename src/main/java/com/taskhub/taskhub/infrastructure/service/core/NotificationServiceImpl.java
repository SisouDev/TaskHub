package com.taskhub.taskhub.infrastructure.service.core;

import com.taskhub.taskhub.domain.dto.request.core.NotificationRequestDTO;
import com.taskhub.taskhub.domain.dto.response.core.NotificationResponseDTO;
import com.taskhub.taskhub.domain.entities.core.Notification;
import com.taskhub.taskhub.domain.repository.core.NotificationRepository;
import com.taskhub.taskhub.domain.service.core.NotificationService;
import com.taskhub.taskhub.exceptions.core.NotificationNotFoundException;
import com.taskhub.taskhub.infrastructure.converter.core.NotificationConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);
    private final NotificationRepository notificationRepository;
    private final NotificationConverter notificationConverter;

    @Autowired
    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationConverter notificationConverter) {
        this.notificationRepository = notificationRepository;
        this.notificationConverter = notificationConverter;
    }

    @Override
    public NotificationResponseDTO sendToUser(Long userId, NotificationRequestDTO dto) {
        logger.info("Sending notification to user with ID: {}", userId);

        Notification notification = notificationConverter.toEntity(dto);
        notification.setId(userId);
        notification.setRead(false);
        notification.setSendDate(LocalDateTime.now());

        Notification saved = notificationRepository.save(notification);
        logger.info("Notification sent successfully. ID: {}", saved.getId());

        return notificationConverter.toResponseDTO(saved);
    }


    @Override
    public List<NotificationResponseDTO> getUserNotifications(Long userId) {
        logger.info("Fetching notifications for user ID: {}", userId);

        List<Notification> notifications = notificationRepository.findByRecipientId(userId);
        return notifications.stream()
                .map(notificationConverter::toResponseDTO)
                .toList();
    }

    @Override
    public void markAsRead(Long notificationId) {
        logger.info("Marking notification as read. ID: {}", notificationId);

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException("Notification not found with ID: " + notificationId));

        notification.setRead(true);
        notificationRepository.save(notification);

        logger.info("Notification marked as read. ID: {}", notificationId);
    }

}
