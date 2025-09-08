package com.ifce.edital360.mapper;

import com.ifce.edital360.dto.edital.*;
import com.ifce.edital360.model.edital.*;

import java.util.List;
import java.util.stream.Collectors;

public class NoticeMapper {
    public static Notice toEntity(NoticeCreateDto dto, String pdfUrl){
        Notice notice = new Notice();

        notice.setTitle(dto.title());
        notice.setDescription(dto.description());
        notice.setRemuneration(dto.remuneration());
        notice.setInitialDate(dto.initialDate());
        notice.setEndDate(dto.endDate());
        notice.setExamDate(dto.examDate());
        notice.setSubscription(dto.subscription());
        notice.setPdfUrl(pdfUrl);

        notice.setPhases(dto.phases()
                .stream()
                .map(p -> new Phase(p.order(), p.exam()))
                .collect(Collectors.toList()));

        notice.setRoles(dto.roles()
                .stream()
                .map(r -> new NoticeRole(r.role(), r.vacancies()))
                .collect(Collectors.toList()));

        notice.setRequirements(dto.requirements());
        notice.setDocuments(dto.documents());
        if (dto.quotas() != null) {
            Cota cota = new Cota();
            cota.setVagasPcd(dto.quotas().getVagasPcd());
            cota.setVagasNegros(dto.quotas().getVagasNegros());
            cota.setVagasIndigenas(dto.quotas().getVagasIndigenas());
            cota.setOutrasCotas(dto.quotas().getOutrasCotas());
            notice.setQuotas(cota);
        }

        if (dto.schedule() != null) {
            notice.setSchedule(dto.schedule().stream()
                    .map(s -> new ScheduleItem(s.description(), s.date()))
                    .collect(Collectors.toList()));
        }

        return notice;
    }

    public static NoticeResponseDto toDto(Notice entity) {
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
                        .toList()
        );
    }

    public static List<NoticeResponseDto> toDtoList(List<Notice> entities) {
        return entities.stream().map(NoticeMapper::toDto).toList();
    }
}
