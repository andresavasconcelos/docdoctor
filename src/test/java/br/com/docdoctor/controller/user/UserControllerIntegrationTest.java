package br.com.docdoctor.controller.user;

import br.com.docdoctor.dto.UserRequestDTO;
import br.com.docdoctor.dto.UserResponseDTO;
import br.com.docdoctor.entities.User;
import br.com.docdoctor.enums.UserTypeEnum;
import br.com.docdoctor.service.user.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private IUserService userService;

    @Test
    void createUser_ShouldReturnCreated() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        UserRequestDTO request = new UserRequestDTO(
                "Maria de Carmo",
                "maria.carmo@example.com",
                "+55 11 95877-4455",
                LocalDate.of(2002, 5, 25),
                UserTypeEnum.ADMIN
        );

        UserResponseDTO response = new UserResponseDTO(
                10L,
                "Maria de Carmo",
                "maria.carmo@example.com",
                "+55 11 95877-4455",
                LocalDate.of(2002, 5, 25),
                UserTypeEnum.ADMIN
        );

        Mockito.when(userService.create(any(UserRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(result -> {
                    if (result.getResolvedException() != null) {
                        result.getResolvedException().printStackTrace();
                    }
                })
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fullName", is("Maria de Carmo")))
                .andExpect(jsonPath("$.email", is("maria.carmo@example.com")))
                .andExpect(jsonPath("$.phone", is("+55 11 95877-4455")))
                .andExpect(jsonPath("$.birthDate", is("2002-05-25")))
                .andExpect(jsonPath("$.userType", is("ADMIN")));
    }

    @Test
    void createUser_WithInvalidData_ShouldReturnBadRequest() throws Exception {
        UserRequestDTO invalidRequest = new UserRequestDTO("Maria de Carmo", "ma@tes.com", "+5511958774455", LocalDate.of(2002, 05, 25), UserTypeEnum.ADMIN);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUserById_WhenExists_ShouldReturnUser() throws Exception {
        UserResponseDTO response = new UserResponseDTO(
                7L,
                "Carlos Silva",
                "joao@example.com",
                "+55 11 95877-4455",
                LocalDate.of(2025, 5, 11),
                UserTypeEnum.ADMIN
        );

        Mockito.when(userService.findById(7L)).thenReturn(response);

        mockMvc.perform(get("/api/users/{id}", 7L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(7)));
    }

    @Test
    void getUserById_WhenNotExists_ShouldReturnNotFound() throws Exception {
        Mockito.when(userService.findById(anyLong())).thenReturn(null);

        mockMvc.perform(get("/api/users/{id}", 99L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void listPagedUsers_ShouldReturnPage() throws Exception {
        User user = new User(3L, "Joao Almeira","user@test.com", "password", LocalDate.of(2002, 05, 25), UserTypeEnum.ADMIN);
        Page<User> page = new PageImpl<>(Collections.singletonList(user));

        Mockito.when(userService.listPaged(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/users/paged")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sortBy", "id")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser_WhenExists_ShouldReturnNoContent() throws Exception {
        Mockito.doNothing().when(userService).remove(anyLong());

        mockMvc.perform(delete("/api/users/{id}", 11L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}