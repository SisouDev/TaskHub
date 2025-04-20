package com.taskhub.taskhub.controller.taskmanagement;

import com.taskhub.taskhub.domain.dto.request.taskmanagement.TaskRequestDTO;
import com.taskhub.taskhub.domain.dto.response.taskmanagement.TaskResponseDTO;
import com.taskhub.taskhub.domain.enums.Status;
import com.taskhub.taskhub.domain.service.taskmanagement.TaskService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @InjectMocks
    private TaskController controller;

    @Mock
    private TaskService taskService;

    @Test
    void testCreateTask() {
        TaskRequestDTO request = new TaskRequestDTO(
                "Tarefa A", "Descrição da tarefa", LocalDateTime.now().plusDays(5),
                Status.TODO, 1L, Set.of("Bug"), Set.of("Dev"), Set.of(1L)
        );
        TaskResponseDTO response = new TaskResponseDTO(1L, "Tarefa A", "Descrição da tarefa", LocalDateTime.now(), LocalDateTime.now(), Status.TODO, "Projeto", Set.of("Bug"), Set.of("Arroz"), Set.of("Dev"));

        when(taskService.create(request)).thenReturn(response);

        ResponseEntity<TaskResponseDTO> result = controller.createTask(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertNotNull(result.getBody());
        assertEquals("Tarefa A", result.getBody().title());
    }

    @Test
    void testChangeStatus() {
        doNothing().when(taskService).changeStatus(1L, Status.COMPLETED);

        ResponseEntity<Void> result = controller.changeStatus(1L, Status.COMPLETED);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        verify(taskService).changeStatus(1L, Status.COMPLETED);
    }
}
