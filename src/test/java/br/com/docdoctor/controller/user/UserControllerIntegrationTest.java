package br.com.docdoctor.controller.user;

import br.com.docdoctor.dto.UserRequestDTO;
import br.com.docdoctor.dto.UserResponseDTO;
import br.com.docdoctor.entities.User;
import br.com.docdoctor.enums.UserTypeEnum;
import br.com.docdoctor.service.user.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
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
import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
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
    private IUserService mockUserService;

    @Autowired
    private IUserService userService;

    private Long userId;
    private UserResponseDTO testUserResponse;

    @BeforeEach
    void setUp() {
        testUserResponse = new UserResponseDTO(
                1L,
                "Test User",
                UUID.randomUUID() + "test@example.com",
                "+55 11 99999-9999",
                LocalDate.of(2000, 1, 1),
                UserTypeEnum.ADMIN
        );

        when(mockUserService.create(any(UserRequestDTO.class))).thenReturn(testUserResponse);
        when(mockUserService.findById(anyLong())).thenReturn(testUserResponse);

        UserRequestDTO userToCreate = new UserRequestDTO(
                "Maria de Carmo",
                UUID.randomUUID() + "@test.com",
                "+55 11 99999-9999",
                LocalDate.of(2002, 5, 25),
                UserTypeEnum.ADMIN
        );

        UserResponseDTO response = userService.create(userToCreate);
        this.userId = response.id();
    }

    @Test
    void createUser_ShouldReturnCreated() throws Exception {
        UserRequestDTO request = createValidUserRequestDTO();

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.fullName", is(testUserResponse.fullName())))
                .andExpect(jsonPath("$.email", is(testUserResponse.email())))
                .andExpect(jsonPath("$.phone", is(testUserResponse.phone())))
                .andExpect(jsonPath("$.birthDate", is("2000-01-01")))
                .andExpect(jsonPath("$.userType", is("ADMIN")));
    }

    @Test
    void createUser_WithInvalidData_ShouldReturnBadRequest() throws Exception {
        UserRequestDTO invalidRequest = new UserRequestDTO(
                "",
                "invalid-email",
                "123",
                null,
                null
        );

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidRequest)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getUserById_WhenExists_ShouldReturnUser() throws Exception {
        mockMvc.perform(get("/api/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getUserById_WhenNotExists_ShouldReturnNotFound() throws Exception {
        Long nonExistentId = 99999L;

        mockMvc.perform(get("/api/users/{id}", nonExistentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Usuário não encontrado"))
                .andExpect(jsonPath("$.message").value("Usuário de id " + nonExistentId + " não encontrado"));
    }

    @Test
    void listPagedUsers_ShouldReturnPage() throws Exception {
        Page<User> page = new PageImpl<>(Collections.singletonList(
                new User(userId,
                        "Maria de Carmo",
                        UUID.randomUUID() + "@test.com",
                        "+55 11 99999-9999",
                        LocalDate.of(2002, 5, 25),
                        UserTypeEnum.ADMIN)
        ));

        when(mockUserService.listPaged(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/users/paged")
                        .param("page", "0")
                        .param("size", "10")
                        .param("sort", "id,asc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void deleteUser_WhenExists_ShouldReturnNoContent() throws Exception {
        mockMvc.perform(delete("/api/users/{id}", userId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private UserRequestDTO createValidUserRequestDTO() {
        return new UserRequestDTO(
                "Test User",
                testUserResponse.email(),
                "+55 11 99999-9999",
                LocalDate.of(2000, 1, 1),
                UserTypeEnum.ADMIN
        );
    }
}