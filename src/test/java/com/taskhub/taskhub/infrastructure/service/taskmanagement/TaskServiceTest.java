package com.taskhub.taskhub.infrastructure.service.taskmanagement;

import com.taskhub.taskhub.domain.entities.core.User;
import com.taskhub.taskhub.domain.entities.taskmanagement.Task;
import com.taskhub.taskhub.domain.enums.Status;
import com.taskhub.taskhub.domain.repository.core.UserRepository;
import com.taskhub.taskhub.domain.repository.projectmanagement.ProjectRepository;
import com.taskhub.taskhub.domain.repository.taskmanagement.TagRepository;
import com.taskhub.taskhub.domain.repository.taskmanagement.TaskRepository;
import com.taskhub.taskhub.exceptions.taskmanagement.TaskNotFoundException;
import com.taskhub.taskhub.exceptions.user.UserNotFoundException;
import com.taskhub.taskhub.infrastructure.converter.taskmanagement.TaskConverter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;
    @Mock private TaskConverter taskConverter;
    @Mock private ProjectRepository projectRepository;
    @Mock private UserRepository userRepository;
    @Mock private TagRepository tagRepository;
    @InjectMocks
    private TaskServiceImpl service;

    @Test
    void testAssignUser_shouldThrowIfUserNotFound() {
        Task task = new Task();
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> service.assignUserToTask(1L, 2L));
    }

    @Test
    void testChangeStatus_shouldThrowIfTaskNotFound() {
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> service.changeStatus(1L, Status.COMPLETED));
    }
    @Test
    void testAssignUser_shouldAddUserToTask() {
        Task task = new Task();
        User user = new User(); user.setId(2L);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));

        service.assignUserToTask(1L, 2L);
        assertTrue(task.getUsers().contains(user));
        verify(taskRepository).save(task);
    }

    @Test
    void testChangeStatus_shouldUpdateAndSaveTask() {
        Task task = new Task();
        task.setStatus(Status.TODO);

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        service.changeStatus(1L, Status.COMPLETED);

        assertEquals(Status.COMPLETED, task.getStatus());
        verify(taskRepository).save(task);
    }

}