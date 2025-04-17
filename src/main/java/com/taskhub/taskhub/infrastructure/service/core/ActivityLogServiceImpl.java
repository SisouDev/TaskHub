package com.taskhub.taskhub.infrastructure.service.core;

import com.taskhub.taskhub.domain.dto.response.core.ActivityLogResponseDTO;
import com.taskhub.taskhub.domain.entities.core.ActivityLog;
import com.taskhub.taskhub.domain.entities.core.User;
import com.taskhub.taskhub.domain.repository.core.ActivityLogRepository;
import com.taskhub.taskhub.domain.service.core.ActivityLogService;
import com.taskhub.taskhub.infrastructure.converter.core.ActivityLogConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityLogServiceImpl implements ActivityLogService {
    private final Logger logger = LoggerFactory.getLogger(ActivityLogServiceImpl.class);
    private final ActivityLogRepository activityLogRepository;
    private final ActivityLogConverter activityLogConverter;

    @Autowired
    public ActivityLogServiceImpl(ActivityLogRepository activityLogRepository, ActivityLogConverter activityLogConverter) {
        this.activityLogRepository = activityLogRepository;
        this.activityLogConverter = activityLogConverter;
    }

    @Override
    public void log(String action, String entityName, Long entityId, User performedBy) {
        logger.info("Logging activity: {} on {} with ID {} by user {}", action, entityName, entityId, performedBy.getId());

        ActivityLog log = new ActivityLog();
        log.setActivity(action);
        log.setEntity(entityName);
        log.setEntityId(entityId);
        log.setUser(performedBy);
        log.setDate(LocalDateTime.now());

        activityLogRepository.save(log);
    }

    @Override
    public List<ActivityLogResponseDTO> getLogsByUser(Long userId) {
        logger.info("Fetching logs for user ID: {}", userId);

        List<ActivityLog> logs = activityLogRepository.findByUserId(userId);
        return logs.stream()
                .map(activityLogConverter::toResponseDTO)
                .toList();
    }

    @Override
    public List<ActivityLogResponseDTO> getLogsByEntity(String entity, Long entityId) {
        logger.info("Fetching logs for entity: {} with ID: {}", entity, entityId);

        List<ActivityLog> logs = activityLogRepository.findByEntityAndEntityId(entity, entityId);
        return logs.stream()
                .map(activityLogConverter::toResponseDTO)
                .toList();
    }
}
