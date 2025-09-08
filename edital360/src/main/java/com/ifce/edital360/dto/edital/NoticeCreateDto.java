package com.ifce.edital360.dto.edital;

import com.ifce.edital360.model.edital.Requirement;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record NoticeCreateDto(
        @NotBlank String title,
        @NotBlank String description,
        @Positive BigDecimal remuneration,
        @NotNull LocalDate initialDate,
        @NotNull LocalDate endDate,
        @NotNull LocalDate examDate,
        @NotEmpty List<PhaseDto> phases,
        @NotEmpty List<NoticeRoleDto> roles,
        @NotEmpty Requirement requirements,
        @NotEmpty List<String> documents,
        CotaDto quotas,
        @NotNull @Positive BigDecimal subscription,
        MultipartFile pdf,
        List<ScheduleItemDto> schedule
        ) {
}
