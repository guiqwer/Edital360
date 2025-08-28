package com.ifce.edital360.dto.notices;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public record NoticeDto(
        @NotBlank String title,
        @NotBlank String description,
        Float remuneration,
        @NotNull LocalDateTime initialDate,
        @NotNull LocalDateTime endDate,
        @NotNull LocalDateTime examDate,
        List<PhaseDto> phaseDtolist,
        List<NoticeRoleDto> noticeRoleDtoList,
        List<String> requirements,
        List<String> documents,
        List<ExternalLinksDto> externalLinksDto,
        List<AnnouncementsDto> announcementsList,
        List<String> quotas,
        @NotNull Float subscription,
        MultipartFile pdf,
        ScheduleItemDto scheduleItemDto
        ) {
}
