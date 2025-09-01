package com.ifce.edital360.dto.notices;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record NoticeResponseDto(
        UUID id,
        String title,
        String description,
        BigDecimal remuneration,
        LocalDateTime initialDate,
        LocalDateTime endDate,
        LocalDateTime createdAt,
        LocalDateTime examDate,
        List<PhaseDto> phases,
        List<NoticeRoleDto> roles,
        List<String> requirements,
        List<String> documents,
        List<ExternalLinksDto> externalLinks,
        List<AnnouncementsDto> announcements,
        List<String> quotas,
        BigDecimal subscription,
        String pdfUrl,
        List<ScheduleItemDto> schedule
) {
}
