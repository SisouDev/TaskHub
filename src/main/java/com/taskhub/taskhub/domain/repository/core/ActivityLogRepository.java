package com.taskhub.taskhub.domain.repository.core;

import com.taskhub.taskhub.domain.entities.core.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
    List<ActivityLog> findByUserId(Long userId);

    List<ActivityLog> findByEntityAndEntityId(String entity, Long entityId);
}
