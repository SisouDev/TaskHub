package com.taskhub.taskhub.controller.core;

import com.taskhub.taskhub.domain.dto.response.core.ActivityLogResponseDTO;
import com.taskhub.taskhub.domain.service.core.ActivityLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activity-logs")
@RequiredArgsConstructor
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ActivityLogResponseDTO>> getLogsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(activityLogService.getLogsByUser(userId));
    }

    @GetMapping("/entity")
    public ResponseEntity<List<ActivityLogResponseDTO>> getLogsByEntity(
            @RequestParam String entity,
            @RequestParam Long entityId) {
        return ResponseEntity.ok(activityLogService.getLogsByEntity(entity, entityId));
    }
}
