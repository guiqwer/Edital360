package com.ifce.edital360.dto.notices;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record NoticeCreateDto(
        @NotBlank String title,
        @NotBlank String description,
        @Positive BigDecimal remuneration,
        @NotNull LocalDateTime initialDate,
        @NotNull LocalDateTime endDate,
        @NotNull LocalDateTime examDate,
        @NotEmpty List<PhaseDto> phases,
        @NotEmpty List<NoticeRoleDto> roles,
        @NotEmpty List<String> requirements,
        @NotEmpty List<String> documents,
        List<ExternalLinksDto> externalLinks,
        List<AnnouncementsDto> announcements,
        List<String> quotas,
        @NotNull @Positive BigDecimal subscription,
        MultipartFile pdf,
        List<ScheduleItemDto> schedule
        ) {
}
