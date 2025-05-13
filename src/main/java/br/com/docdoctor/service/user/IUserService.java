package br.com.docdoctor.service.user;

import br.com.docdoctor.dto.UserRequestDTO;
import br.com.docdoctor.dto.UserResponseDTO;
import br.com.docdoctor.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {

    UserResponseDTO create(UserRequestDTO newUser);
    UserResponseDTO update(Long id, UserRequestDTO dto);
    List<User> listAll();
    Page<User> listPaged(Pageable pageable);
    UserResponseDTO findById(Long id);
    void remove(Long id);
}
