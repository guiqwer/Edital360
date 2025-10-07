package com.ifce.edital360.dto.edital;

import com.ifce.edital360.dto.isencao.ExemptionDto;
import com.ifce.edital360.model.edital.Requirement;
import com.ifce.edital360.model.enums.StatusNotice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record NoticeResponseDto(
        UUID id,
        String title,
        String description,
        BigDecimal remuneration,
        LocalDate initialDate,
        LocalDate endDate,
        LocalDate createdAt,
        LocalDate examDate,
        List<PhaseDto> phases,
        List<NoticeRoleDto> roles,
        Requirement requirements,
        List<String> documents,
        CotaDto quotas,
        BigDecimal subscription,
        String pdfUrl,
        List<ScheduleItemDto> schedule,
        Integer vacancies,
        ExemptionDto exemption,
        StatusNotice statusNotice
) {
}
