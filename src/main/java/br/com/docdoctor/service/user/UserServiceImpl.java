package br.com.docdoctor.service.user;

import br.com.docdoctor.dto.UserRequestDTO;
import br.com.docdoctor.dto.UserResponseDTO;
import br.com.docdoctor.entities.User;
import br.com.docdoctor.exception.UserNotFoundException;
import br.com.docdoctor.mapper.UserMapper;
import br.com.docdoctor.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository repository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository repository, UserMapper userMapper) {
        this.repository = repository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDTO create(UserRequestDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User savedUser = repository.save(user);
        return userMapper.toRespondeDTO(savedUser);
    }

    @Override
    public List<UserResponseDTO> listAll() {
        return repository.findAll().stream()
                .map(user -> userMapper.toRespondeDTO(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponseDTO findUserById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.toRespondeDTO(user);
    }

    @Override
    public UserResponseDTO update(Long id) {
        User existingUser = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        userMapper.toRespondeDTO(existingUser);
        existingUser.setId(id);
        User updatedUser = repository.save(existingUser);

        return userMapper.toRespondeDTO(updatedUser);
    }

    @Override
    public void remove(Long id) {
        if (!repository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
