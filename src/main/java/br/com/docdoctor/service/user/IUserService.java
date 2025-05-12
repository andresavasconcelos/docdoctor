package br.com.docdoctor.service.user;

import br.com.docdoctor.dto.UserRequestDTO;
import br.com.docdoctor.dto.UserResponseDTO;

import java.util.List;

public interface IUserService {

    UserResponseDTO create(UserRequestDTO newUser);
    UserResponseDTO update(Long id);
    List<UserResponseDTO> listAll();
    UserResponseDTO findUserById(Long id);
    void remove(Long id);
}
