package com.ifce.edital360.dto.notices;

import com.ifce.edital360.model.enums.Exam;

public record PhaseDto(
    Integer order,
    Exam exam
) {
}
