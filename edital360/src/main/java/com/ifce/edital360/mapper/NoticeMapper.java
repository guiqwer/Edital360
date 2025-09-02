package com.ifce.edital360.mapper;

import com.ifce.edital360.dto.notices.*;
import com.ifce.edital360.model.notices.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.stream.Collectors;

public class NoticeMapper {
    public static Notice toEntity(NoticeCreateDto dto, String pdfUrl, List<String> announcementsUrls){
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
        notice.setQuotas(dto.quotas());

        if (dto.schedule() != null) {
            notice.setSchedule(dto.schedule().stream()
                    .map(s -> new ScheduleItem(s.description(), s.date()))
                    .collect(Collectors.toList()));
        }

        if (dto.externalLinks() != null) {
            notice.setExternalLinks(dto.externalLinks().stream()
                    .map(link -> new ExternalLinks(link.url(), null, notice))
                    .collect(Collectors.toList()));
        }

        //preciso fazer a lógica dos announcements depois quando tiver upload de arquivo
        if (announcementsUrls != null && !announcementsUrls.isEmpty()) {
            notice.setAnnouncements(
                    announcementsUrls.stream()
                            .map(url -> new Announcements(url, null, notice))
                            .toList()
            );
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
                entity.getExternalLinks().stream()
                        .map(e -> new ExternalLinksDto(e.getId(), e.getUrl(), e.getCreatedAt()))
                        .toList(),
                entity.getAnnouncements().stream()
                        .map(a -> new AnnouncementsDto(a.getId(), a.getUrl(), a.getCreatedAt(), null))
                        .toList(),
                entity.getQuotas(),
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
