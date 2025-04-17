package com.taskhub.taskhub.domain.service.core;

import com.taskhub.taskhub.domain.dto.response.core.ActivityLogResponseDTO;
import com.taskhub.taskhub.domain.entities.core.User;

import java.util.List;

public interface ActivityLogService {
    void log(String action, String entityName, Long entityId, User performedBy);
    List<ActivityLogResponseDTO> getLogsByUser(Long userId);
    List<ActivityLogResponseDTO> getLogsByEntity(String entity, Long entityId);
}
