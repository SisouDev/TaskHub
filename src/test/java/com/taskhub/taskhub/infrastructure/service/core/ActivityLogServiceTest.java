package com.taskhub.taskhub.infrastructure.service.core;

import com.taskhub.taskhub.domain.dto.response.core.ActivityLogResponseDTO;
import com.taskhub.taskhub.domain.entities.core.ActivityLog;
import com.taskhub.taskhub.domain.entities.core.User;
import com.taskhub.taskhub.domain.repository.core.ActivityLogRepository;
import com.taskhub.taskhub.infrastructure.converter.core.ActivityLogConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActivityLogServiceImplTest {

    @Mock private ActivityLogRepository repository;
    @Mock
    private ActivityLogConverter converter;
    @InjectMocks
    private ActivityLogServiceImpl service;

    @Test
    void testLog_shouldCallSave() {
        User user = new User(); user.setId(1L);
        service.log("CREATE", "Task", 10L, user);
        verify(repository, times(1)).save(any(ActivityLog.class));
    }

    @Test
    void testGetLogsByUser_shouldReturnList() {
        User user = new User(); user.setId(1L);
        ActivityLog log = new ActivityLog();
        log.setId(1L);
        log.setActivity("CREATE");
        log.setEntity("Task");
        log.setEntityId(10L);
        log.setDate(LocalDateTime.now());
        log.setUser(user);

        ActivityLogResponseDTO dto = new ActivityLogResponseDTO(
                log.getId(), log.getActivity(), log.getDate(),
                log.getEntity(), log.getEntityId(), user.getId()
        );

        when(repository.findByUserId(1L)).thenReturn(List.of(log));
        when(converter.toResponseDTO(log)).thenReturn(dto);

        List<ActivityLogResponseDTO> result = service.getLogsByUser(1L);

        assertEquals(1, result.size());
        assertEquals(dto, result.getFirst());
    }

    @Test
    void testGetLogsByEntity_shouldReturnList() {
        User user = new User(); user.setId(1L);
        ActivityLog log = new ActivityLog();
        log.setId(1L);
        log.setActivity("CREATE");
        log.setEntity("Task");
        log.setEntityId(10L);
        log.setDate(LocalDateTime.now());
        log.setUser(user);

        ActivityLogResponseDTO dto = new ActivityLogResponseDTO(
                log.getId(), log.getActivity(), log.getDate(),
                log.getEntity(), log.getEntityId(), user.getId()
        );

        when(repository.findByEntityAndEntityId("Task", 10L)).thenReturn(List.of(log));
        when(converter.toResponseDTO(log)).thenReturn(dto);

        List<ActivityLogResponseDTO> result = service.getLogsByEntity("Task", 10L);

        assertEquals(1, result.size());
        assertEquals(dto, result.getFirst());
    }

    @Test
    void testLog_shouldSetCorrectFields() {
        ArgumentCaptor<ActivityLog> captor = ArgumentCaptor.forClass(ActivityLog.class);
        User user = new User(); user.setId(1L);

        service.log("UPDATE", "Project", 5L, user);
        verify(repository).save(captor.capture());

        ActivityLog log = captor.getValue();
        assertEquals("UPDATE", log.getActivity());
        assertEquals("Project", log.getEntity());
        assertEquals(5L, log.getEntityId());
        assertEquals(user, log.getUser());
        assertNotNull(log.getDate());
    }

    @Test
    void testGetLogsByUser_shouldReturnEmptyListIfNoLogs() {
        when(repository.findByUserId(2L)).thenReturn(List.of());

        List<ActivityLogResponseDTO> result = service.getLogsByUser(2L);
        assertTrue(result.isEmpty());
    }
}

