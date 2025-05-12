package br.com.docdoctor.dto;

import br.com.docdoctor.enums.UserTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public record UserResponseDTO(
        Long id,
        String fullName,
        String email,
        String phone,
        Date birthDate,
        UserTypeEnum userType
) {}