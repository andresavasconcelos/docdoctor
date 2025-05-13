package br.com.docdoctor.dto;

public record FieldErrorDTO(
        String field,         // Nome do campo com erro (ex: "birthDate")
        String rejectedValue, // Valor rejeitado (ex: "2025-05-13")
        String message        // Mensagem de erro (ex: "Data deve ser passada")
) {}