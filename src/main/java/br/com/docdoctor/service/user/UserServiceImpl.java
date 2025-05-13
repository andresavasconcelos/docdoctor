package br.com.docdoctor.service.user;

import br.com.docdoctor.dto.UserRequestDTO;
import br.com.docdoctor.dto.UserResponseDTO;
import br.com.docdoctor.entities.User;
import br.com.docdoctor.exception.UserNotFoundException;
import br.com.docdoctor.mapper.UserMapper;
import br.com.docdoctor.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDTO create(UserRequestDTO userDTO) {
        try {
            log.info("Creating user with email: {}", userDTO.email());
            User user = userMapper.toEntity(userDTO);
            User savedUser = repository.save(user);
            return userMapper.toResponseDTO(savedUser);
        } catch (DataIntegrityViolationException e) {
            log.error("Erro ao criar usuário - possível e-mail duplicado: {}", userDTO.email(), e);
            throw e;
        }
    }

    @Override
    public List<User> listAll() {
        log.debug("Buscando todos os usuários");
        List<User> users = repository.findAll();
        log.info("Total de usuários encontrados: {}", users.size());
        return users;
    }

    @Override
    public Page<User> listPaged(Pageable pageable) {
        log.debug("Buscando usuários paginados com página: {}, tamanho: {}", pageable.getPageNumber(), pageable.getPageSize());
        Page<User> page = repository.findAll(pageable);
        log.info("Página obtida com {} usuários", page.getNumberOfElements());
        return page;
    }

    @Override
    public UserResponseDTO findById(Long id) {
        log.debug("Buscando usuário pelo ID: {}", id);
        User user = repository.findById(id).orElseThrow(() -> {
            log.warn("Usuário não encontrado com o ID: {}", id);
            return new UserNotFoundException(id);
        });
        log.info("Usuário encontrado com o ID: {}", id);
        return userMapper.toResponseDTO(user);
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO dto) {
        log.info("Atualizando usuário com o ID: {}", id);
        findById(id);
        User userToUpdate = userMapper.toEntity(dto);
        userToUpdate.setId(id);
        User updated = repository.save(userToUpdate);
        log.info("Usuário atualizado com sucesso com o ID: {}", updated.getId());
        return userMapper.toResponseDTO(updated);
    }

    @Override
    public void remove(Long id) {
        log.info("Tentando remover usuário com o ID: {}", id);
        if (!repository.existsById(id)) {
            log.warn("Não foi possível remover o usuário; ID não encontrado: {}", id);
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
        log.info("Usuário removido com sucesso com o ID: {}", id);
    }
}
