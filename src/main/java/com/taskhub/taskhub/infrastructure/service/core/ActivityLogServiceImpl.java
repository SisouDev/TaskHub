package com.taskhub.taskhub.infrastructure.service.core;

import com.taskhub.taskhub.domain.dto.response.core.ActivityLogResponseDTO;
import com.taskhub.taskhub.domain.entities.core.User;
import com.taskhub.taskhub.domain.service.core.ActivityLogService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {
    @Override
    public void log(String action, String entityName, Long entityId, User performedBy) {

    }

    @Override
    public List<ActivityLogResponseDTO> getLogsByUser(Long userId) {
        return List.of();
    }

    @Override
    public List<ActivityLogResponseDTO> getLogsByEntity(String entity, Long entityId) {
        return List.of();
    }
}
