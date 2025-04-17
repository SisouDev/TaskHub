package com.taskhub.taskhub.infrastructure.converter.core;

import com.taskhub.taskhub.domain.dto.request.core.NotificationRequestDTO;
import com.taskhub.taskhub.domain.dto.response.core.ActivityLogResponseDTO;
import com.taskhub.taskhub.domain.dto.response.core.NotificationResponseDTO;
import com.taskhub.taskhub.domain.entities.core.ActivityLog;
import com.taskhub.taskhub.domain.entities.core.Notification;
import org.springframework.stereotype.Component;

@Component
public class NotificationConverter {
    public Notification toEntity(NotificationRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        Notification notification = new Notification();
        notification.setMessage(requestDTO.message());
        notification.setRead(requestDTO.read());
        notification.setSendDate(requestDTO.sendDate());

        return notification;
    }

    public NotificationResponseDTO toResponseDTO(Notification notification) {
        if (notification == null) {
            return null;
        }

        return new NotificationResponseDTO(
                notification.getId(),
                notification.getMessage(),
                notification.getRead(),
                notification.getSendDate(),
                notification.getRecipient().getId()
        );
    }

    public ActivityLogResponseDTO toResponseDTO(ActivityLog activityLog) {
        if (activityLog == null) {
            return null;
        }

        return new ActivityLogResponseDTO(
                activityLog.getId(),
                activityLog.getActivity(),
                activityLog.getDate(),
                activityLog.getEntity(),
                activityLog.getEntityId(),
                activityLog.getUser() != null ? activityLog.getUser().getId() : null
        );
    }
}
