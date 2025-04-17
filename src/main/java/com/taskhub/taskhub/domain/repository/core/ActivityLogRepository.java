package com.taskhub.taskhub.domain.repository.core;

import com.taskhub.taskhub.domain.entities.core.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, Long> {
}
