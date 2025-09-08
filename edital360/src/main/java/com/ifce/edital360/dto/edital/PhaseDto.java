package com.ifce.edital360.dto.edital;

import com.ifce.edital360.model.enums.Exam;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PhaseDto(
    @Positive Integer order,
    @NotNull Exam exam
) {
}
