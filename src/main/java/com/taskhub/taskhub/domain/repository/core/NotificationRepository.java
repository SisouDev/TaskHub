package com.taskhub.taskhub.domain.repository.core;

import com.taskhub.taskhub.domain.entities.core.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
