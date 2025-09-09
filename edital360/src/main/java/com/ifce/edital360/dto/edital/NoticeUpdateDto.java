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

public record NoticeUpdateDto(
        String title,
        String description,
        @Positive BigDecimal remuneration,
        LocalDate initialDate,
        LocalDate endDate,
        LocalDate examDate,
        List<PhaseDto> phases,
        List<NoticeRoleDto> roles,
        Requirement requirements,
        List<String> documents,
        CotaDto quotas,
        @Positive BigDecimal subscription,
        MultipartFile pdf,
        List<ScheduleItemDto> schedule
) {
}
