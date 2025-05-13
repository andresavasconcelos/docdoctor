package br.com.docdoctor.dto;

import br.com.docdoctor.enums.UserTypeEnum;

import java.time.LocalDate;

public record UserResponseDTO(
        Long id,
        String fullName,
        String email,
        String phone,
        LocalDate birthDate,
        UserTypeEnum userType
) {}