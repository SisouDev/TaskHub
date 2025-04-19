package com.taskhub.taskhub.infrastructure.service.projectmanagement;

import com.taskhub.taskhub.domain.entities.core.User;
import com.taskhub.taskhub.domain.entities.projectmanagement.Project;
import com.taskhub.taskhub.domain.repository.core.UserRepository;
import com.taskhub.taskhub.domain.repository.projectmanagement.ProjectRepository;
import com.taskhub.taskhub.exceptions.user.UserNotFoundException;
import com.taskhub.taskhub.infrastructure.converter.projectmanagement.ProjectConverter;
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
class ProjectServiceImplTest {

    @Mock private ProjectRepository projectRepository;
    @Mock
    private ProjectConverter projectConverter;
    @Mock private UserRepository userRepository;
    @InjectMocks
    private ProjectServiceImpl projectService;

    @Test
    void testAssignUser_shouldThrowIfUserNotFound() {
        Project project = new Project();
        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(userRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> projectService.assignUser(1L, 2L));
    }

    @Test
    void testChangeLeader_shouldUpdateLeader() {
        Project project = new Project();
        User user = new User(); user.setId(2L);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));

        projectService.changeLeader(1L, 2L);

        assertEquals(user, project.getLeader());
        verify(projectRepository).save(project);
    }

    @Test
    void testRemoveUser_shouldRemoveUserFromProject() {
        User user = new User(); user.setId(2L);
        Project project = new Project();
        project.getUsers().add(user);

        when(projectRepository.findById(1L)).thenReturn(Optional.of(project));
        when(userRepository.findById(2L)).thenReturn(Optional.of(user));

        projectService.removeUser(1L, 2L);

        assertFalse(project.getUsers().contains(user));
        verify(projectRepository).save(project);
    }
}