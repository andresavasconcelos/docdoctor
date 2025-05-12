package br.com.docdoctor.dto;

import br.com.docdoctor.enums.UserTypeEnum;
import jakarta.validation.constraints.*;

import java.util.Date;

public record UserRequestDTO(
        @NotBlank(message = "Nome completo é obrigatório")
        @Size(min = 3, max = 100, message = "Nome completo deve conter entre 3 e 100 caracteres")
        String fullName,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "Informe um e-mail válido")
        String email,

        @NotBlank(message = "Telefone é obrigatório")
        @Pattern(regexp = "^\\+\\d{1,3} \\d{2} \\d{4,5}-\\d{4}$",
                message = "Telefone deve estar no formato internacional (ex: +55 11 98765-4321)")
        String phone,

        @NotNull(message = "Data de nascimento é obrigatória")
        @Past(message = "Data de nascimento deve ser uma data passada")
        Date birthDate,

        @NotNull(message = "Tipo de usuário é obrigatório")
        UserTypeEnum userType
) {}