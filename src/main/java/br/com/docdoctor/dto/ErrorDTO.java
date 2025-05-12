package br.com.docdoctor.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorDTO(
        int status,
        String error,
        String message,
        LocalDateTime timestamp,
        List<String> errors
) {
    public ErrorDTO(int status, String error, String message) {
        this(status, error, message, LocalDateTime.now(), null);
    }

    public ErrorDTO(int status, String error, List<String> errors) {
        this(status, error, null, LocalDateTime.now(), errors);
    }
}