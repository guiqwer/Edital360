package com.ifce.edital360.dto.notices;

import jakarta.validation.constraints.NotBlank;

public record NoticeRoleDto(
        @NotBlank String role,
        Integer vacancies
) {
}
