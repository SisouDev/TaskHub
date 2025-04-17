package com.taskhub.taskhub.infrastructure.converter.core;

import com.taskhub.taskhub.domain.dto.request.core.ActivityLogRequestDTO;
import com.taskhub.taskhub.domain.entities.core.ActivityLog;
import com.taskhub.taskhub.domain.entities.core.User;
import com.taskhub.taskhub.domain.repository.core.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class ActivityLogConverter {

    private final UserRepository userRepository;

    public ActivityLogConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ActivityLog toEntity(ActivityLogRequestDTO requestDTO) {
        if (requestDTO == null) {
            return null;
        }

        User user = userRepository.findById(requestDTO.userId()).orElse(null);

        ActivityLog activityLog = new ActivityLog();
        activityLog.setActivity(requestDTO.activity());
        activityLog.setEntity(requestDTO.entity());
        activityLog.setEntityId(requestDTO.entityId());


        if (user != null) {
            activityLog.setUser(user);
        }

        return activityLog;

    }
}
