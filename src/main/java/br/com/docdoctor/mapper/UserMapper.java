package br.com.docdoctor.mapper;

import br.com.docdoctor.dto.UserRequestDTO;
import br.com.docdoctor.dto.UserResponseDTO;
import br.com.docdoctor.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRequestDTO dto);
    UserRequestDTO toResquestDTO(User entity);
    UserResponseDTO toRespondeDTO(User entity);
}