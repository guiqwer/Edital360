package com.ifce.edital360.dto.edital;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record NoticeRoleDto(
        @NotBlank String role,
        @PositiveOrZero Integer vacancies
) {
}
