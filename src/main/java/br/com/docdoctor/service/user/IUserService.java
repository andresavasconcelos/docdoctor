package br.com.docdoctor.service.user;

import br.com.docdoctor.dto.UserPaginationDTO;
import br.com.docdoctor.dto.UserRequestDTO;
import br.com.docdoctor.dto.UserResponseDTO;
import org.springframework.data.domain.Pageable;

public interface IUserService {

    UserResponseDTO create(UserRequestDTO newUser);
    UserResponseDTO update(Long id);
    UserPaginationDTO listAll(Pageable pageable);
    UserResponseDTO findById(Long id);
    void remove(Long id);
}
