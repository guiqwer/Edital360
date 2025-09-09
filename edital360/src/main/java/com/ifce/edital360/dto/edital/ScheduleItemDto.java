package com.ifce.edital360.dto.edital;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ScheduleItemDto(
        String description,
        @NotNull LocalDateTime date
        ) {
}
