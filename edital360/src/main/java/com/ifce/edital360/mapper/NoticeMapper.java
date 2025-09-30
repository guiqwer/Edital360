package com.ifce.edital360.mapper;

import com.ifce.edital360.dto.edital.*;
import com.ifce.edital360.model.edital.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class NoticeMapper {
    public static Notice toEntity(NoticeCreateDto dto, String pdfUrl){
        Notice notice = new Notice();

        notice.setTitle(dto.getTitle());
        notice.setDescription(dto.getDescription());
        notice.setRemuneration(dto.getRemuneration());
        notice.setInitialDate(dto.getInitialDate());
        notice.setEndDate(dto.getEndDate());
        notice.setExamDate(dto.getExamDate());
        notice.setSubscription(dto.getSubscription());
        notice.setPdfUrl(pdfUrl);

        notice.setPhases(dto.getPhases()
                .stream()
                .map(p -> new Phase(p.getOrder(), p.getExam()))
                .collect(Collectors.toList()));

        notice.setRoles(dto.getRoles()
                .stream()
                .map(r -> new NoticeRole(r.getRole(), r.getVacancies()))
                .collect(Collectors.toList()));

        notice.setRequirements(dto.getRequirements());
        notice.setDocuments(dto.getDocuments());
        if (dto.getQuotas() != null) {
            Cota cota = new Cota();
            cota.setVagasPcd(dto.getQuotas().getVagasPcd());
            cota.setVagasNegros(dto.getQuotas().getVagasNegros());
            cota.setVagasIndigenas(dto.getQuotas().getVagasIndigenas());
            cota.setOutrasCotas(dto.getQuotas().getOutrasCotas());
            notice.setQuotas(cota);
        }

        if (dto.getSchedule() != null) {
            notice.setSchedule(dto.getSchedule().stream()
                    .map(s -> new ScheduleItem(s.getDescription(), s.getDate()))
                    .collect(Collectors.toList()));
        }

        return notice;
    }

    public static NoticeResponseDto toDto(Notice entity) {
        int roleVacancies = entity.getRoles() != null
                ? entity.getRoles().stream()
                .mapToInt(r -> r.getVacancies() != null ? r.getVacancies() : 0)
                .sum()
                : 0;

        int quotaVacancies = 0;
        if (entity.getQuotas() != null) {
            quotaVacancies += entity.getQuotas().getVagasPcd() != null ? entity.getQuotas().getVagasPcd() : 0;
            quotaVacancies += entity.getQuotas().getVagasNegros() != null ? entity.getQuotas().getVagasNegros() : 0;
            quotaVacancies += entity.getQuotas().getVagasIndigenas() != null ? entity.getQuotas().getVagasIndigenas() : 0;
        }

        int totalVacancies = roleVacancies + quotaVacancies;

        return new NoticeResponseDto(
                entity.getId(),
                entity.getTitle(),
                entity.getDescription(),

                entity.getRemuneration(),
                entity.getInitialDate(),
                entity.getEndDate(),
                entity.getCreatedAt(),
                entity.getExamDate(),
                entity.getPhases().stream()
                        .map(p -> new PhaseDto(p.getOrder(), p.getExam()))
                        .toList(),
                entity.getRoles().stream()
                        .map(r -> new NoticeRoleDto(r.getRole(), r.getVacancies()))
                        .toList(),
                entity.getRequirements(),
                entity.getDocuments(),
                entity.getQuotas() != null
                        ? new CotaDto(
                        entity.getQuotas().getVagasPcd(),
                        entity.getQuotas().getVagasNegros(),
                        entity.getQuotas().getVagasIndigenas(),
                        entity.getQuotas().getOutrasCotas()
                )
                        : null,
                entity.getSubscription(),
                entity.getPdfUrl(),
                entity.getSchedule().stream()
                        .map(s -> new ScheduleItemDto(s.getDescription(), s.getDate()))
                        .toList(),
                totalVacancies
        );
    }

    public static Page<NoticeResponseDto> toDtoPaginated(Page<Notice> entities) {
        return entities.map(NoticeMapper::toDto);
    }
}
