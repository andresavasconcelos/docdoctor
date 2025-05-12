package br.com.docdoctor.dto;

import org.springframework.data.domain.Page;
import java.util.List;

public record UserPaginationDTO(
        List<UserResponseDTO> content,
        int currentPage,
        int totalPages,
        long totalItems,
        boolean firstPage,
        boolean lastPage
) {
    public static UserPaginationDTO fromPage(Page<UserResponseDTO> page) {
        return new UserPaginationDTO(
                page.getContent(),
                page.getNumber(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.isLast()
        );
    }
}