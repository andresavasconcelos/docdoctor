package br.com.docdoctor.service.user;

import br.com.docdoctor.dto.UserRequestDTO;
import br.com.docdoctor.dto.UserResponseDTO;
import br.com.docdoctor.entities.User;
import br.com.docdoctor.enums.UserTypeEnum;
import br.com.docdoctor.exception.UserNotFoundException;
import br.com.docdoctor.mapper.UserMapper;
import br.com.docdoctor.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserRepository repository;
    private UserMapper mapper;
    private UserServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = mock(UserRepository.class);
        mapper = mock(UserMapper.class);
        service = new UserServiceImpl(repository, mapper);
    }

    @Test
    void shouldCreateUserSuccessfully() {
        UserRequestDTO requestDTO = new UserRequestDTO(
                "João Silva", "joao@email.com", "11999999999",
                LocalDate.of(1990, 1, 1), UserTypeEnum.VIEWER
        );

        User user = new User();
        user.setFullName("João Silva");
        user.setEmail("joao@email.com");

        User savedUser = new User();
        savedUser.setId(1L);
        savedUser.setFullName("João Silva");
        savedUser.setEmail("joao@email.com");

        UserResponseDTO responseDTO = new UserResponseDTO(
                1L, "João Silva", "joao@email.com", "11999999999",
                LocalDate.of(1990, 1, 1), UserTypeEnum.VIEWER
        );

        when(mapper.toEntity(requestDTO)).thenReturn(user);
        when(repository.save(user)).thenReturn(savedUser);
        when(mapper.toResponseDTO(savedUser)).thenReturn(responseDTO);

        UserResponseDTO result = service.create(requestDTO);

        assertAll("Deve retornar DTO com os dados corretos",
                () -> assertEquals(1L, result.id()),
                () -> assertEquals("João Silva", result.fullName()),
                () -> assertEquals("joao@email.com", result.email())
        );
        verify(repository, times(1)).save(user);
    }

    @Test
    void shouldThrowDataIntegrityViolationExceptionWhenDuplicateEmail() {
        UserRequestDTO requestDTO = new UserRequestDTO(
                "Maria Souza", "maria@email.com", "11888888888",
                LocalDate.of(1985, 5, 10), UserTypeEnum.EDITOR
        );
        User user = new User();

        when(mapper.toEntity(requestDTO)).thenReturn(user);
        when(repository.save(user)).thenThrow(new DataIntegrityViolationException("E-mail duplicado"));

        DataIntegrityViolationException ex = assertThrows(
                DataIntegrityViolationException.class,
                () -> service.create(requestDTO)
        );

        assertTrue(ex.getMessage().contains("E-mail duplicado"));
        verify(repository, times(1)).save(user);
    }

    @Test
    void shouldListAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());
        when(repository.findAll()).thenReturn(users);

        List<User> result = service.listAll();

        assertEquals(users.size(), result.size());
        verify(repository).findAll();
    }

    @Test
    void shouldListPagedUsers() {
        PageRequest pageable = PageRequest.of(0, 2);
        Page<User> page = new PageImpl<>(List.of(new User(), new User()));
        when(repository.findAll(pageable)).thenReturn(page);

        Page<User> result = service.listPaged(pageable);

        assertEquals(2, result.getTotalElements());
        verify(repository).findAll(pageable);
    }

    @Test
    void shouldFindUserById() {
        User user = new User();
        user.setId(1L);
        UserResponseDTO responseDTO = new UserResponseDTO(
                1L, "Carlos Lima", "carlos@email.com", "11777777777",
                LocalDate.of(1980, 3, 15), UserTypeEnum.ADMIN
        );

        when(repository.findById(1L)).thenReturn(Optional.of(user));
        when(mapper.toResponseDTO(user)).thenReturn(responseDTO);

        UserResponseDTO result = service.findById(1L);

        assertEquals(responseDTO, result);
        verify(repository).findById(1L);
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> service.findById(1L));
    }

    @Test
    void shouldUpdateUser() {
        UserRequestDTO requestDTO = new UserRequestDTO(
                "Lucas Costa", "lucas@email.com", "11712345678",
                LocalDate.of(1995, 12, 5), UserTypeEnum.ADMIN
        );
        User existingUser = new User();
        User userToUpdate = new User();
        userToUpdate.setId(1L);
        UserResponseDTO responseDTO = new UserResponseDTO(
                1L, "Lucas Costa", "lucas@email.com", "11712345678",
                LocalDate.of(1995, 12, 5), UserTypeEnum.ADMIN
        );

        when(repository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(mapper.toEntity(requestDTO)).thenReturn(userToUpdate);
        when(repository.save(userToUpdate)).thenReturn(userToUpdate);
        when(mapper.toResponseDTO(userToUpdate)).thenReturn(responseDTO);

        UserResponseDTO result = service.update(1L, requestDTO);

        assertEquals(responseDTO, result);
        verify(repository).save(userToUpdate);
    }

    @Test
    void shouldRemoveUser() {
        when(repository.existsById(1L)).thenReturn(true);

        service.remove(1L);

        verify(repository).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenRemovingNonExistentUser() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(UserNotFoundException.class, () -> service.remove(1L));
    }
}
