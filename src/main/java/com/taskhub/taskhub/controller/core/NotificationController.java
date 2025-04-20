package com.taskhub.taskhub.controller.core;

import com.taskhub.taskhub.domain.dto.request.core.NotificationRequestDTO;
import com.taskhub.taskhub.domain.dto.response.core.NotificationResponseDTO;
import com.taskhub.taskhub.domain.service.core.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/send/{userId}")
    public ResponseEntity<NotificationResponseDTO> sendToUser(@PathVariable Long userId, @RequestBody @Valid NotificationRequestDTO dto) {
        NotificationResponseDTO response = notificationService.sendToUser(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<NotificationResponseDTO>> getUserNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(notificationService.getUserNotifications(userId));
    }

    @PatchMapping("/{id}/read")
    public ResponseEntity<Void> markAsRead(@PathVariable Long id) {
        notificationService.markAsRead(id);
        return ResponseEntity.ok().build();
    }
}
