package com.taskhub.taskhub.domain.dto.taskmanagement;

import com.taskhub.taskhub.domain.enums.Status;

import java.time.LocalDateTime;
import java.util.Set;

public record TaskDTO(Long id,
                      String title,
                      String description,
                      LocalDateTime createdAt,
                      LocalDateTime deadline,
                      Status status,
                      Long projectId,
                      Set<String> tagNames,
                      Set<String> categoryNames,
                      Set<Long> userIds) {
}
