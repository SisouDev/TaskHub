package com.taskhub.taskhub.controller.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskhub.taskhub.domain.dto.request.core.UserRequestDTO;
import com.taskhub.taskhub.domain.dto.response.core.UserResponseDTO;
import com.taskhub.taskhub.domain.enums.Permission;
import com.taskhub.taskhub.domain.service.core.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreateUser() throws Exception {
        UserRequestDTO requestDTO = new UserRequestDTO(
                "Maria", "Karolina", "karol@email.com", "31999999999", null,
                Permission.USER, 1L, 1L
        );

        UserResponseDTO responseDTO = new UserResponseDTO(1L, "Maria", "Karolina", "karol@email.com", "31999999999", null, Permission.USER, null, null);

        Mockito.when(userService.createUser(Mockito.any(UserRequestDTO.class))).thenReturn(responseDTO);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.firstName").value("Maria"))
                .andExpect(jsonPath("$.email").value("karol@email.com"));
    }

    @Test
    void testGetUserById() throws Exception {
        UserResponseDTO responseDTO = new UserResponseDTO(1L, "Maria", "Karolina", "karol@email.com", "31999999999", null, Permission.USER, null, null);

        Mockito.when(userService.getUserById(1L)).thenReturn(responseDTO);

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Maria"));
    }

    @Test
    void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(userService).deleteUserById(1L);
    }
}