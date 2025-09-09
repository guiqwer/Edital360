package com.ifce.edital360.service.notice;

import com.ifce.edital360.dto.edital.NoticeCreateDto;
import com.ifce.edital360.dto.edital.NoticeResponseDto;
import com.ifce.edital360.dto.edital.NoticeUpdateDto;
import com.ifce.edital360.mapper.NoticeMapper;
import com.ifce.edital360.model.edital.*;
import com.ifce.edital360.repository.NoticeRepository;
import com.ifce.edital360.service.localStorage.LocalStorageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private LocalStorageService localStorageService;

    @Value("${app.base-url}")
    private String baseUrl;

    public NoticeResponseDto createNotice(NoticeCreateDto dto) throws IOException {
        String pdfUrl = null;

        if(dto.pdf() != null && !dto.pdf().isEmpty()) {
            var uploadResult = localStorageService.salvar(dto.pdf());
            var linkCru = baseUrl + "/publicos/" + uploadResult;
            pdfUrl = linkCru.replaceAll("\\s+", "_");

        }

        Notice notice = NoticeMapper.toEntity(dto, pdfUrl);

        notice = noticeRepository.save(notice);

        return NoticeMapper.toDto(notice);
    }

    public List<NoticeResponseDto> getAll() {
        List<Notice> notices = noticeRepository.findAll();
        return NoticeMapper.toDtoList(notices);
    }

    public NoticeResponseDto getById(UUID id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aviso não encontrado com ID: " + id));
        return NoticeMapper.toDto(notice);
    }

    public NoticeResponseDto update(UUID id, NoticeUpdateDto dto) throws IOException {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aviso não encontrado com ID: " + id));

        if (dto.title() != null) notice.setTitle(dto.title());
        if (dto.description() != null) notice.setDescription(dto.description());
        if (dto.remuneration() != null) notice.setRemuneration(dto.remuneration());
        if (dto.initialDate() != null) notice.setInitialDate(dto.initialDate());
        if (dto.endDate() != null) notice.setEndDate(dto.endDate());
        if (dto.examDate() != null) notice.setExamDate(dto.examDate());
        if (dto.subscription() != null) notice.setSubscription(dto.subscription());

        if (dto.phases() != null && !dto.phases().isEmpty()) {
            notice.setPhases(dto.phases().stream()
                    .map(p -> new Phase(p.order(), p.exam()))
                    .toList());
        }

        if (dto.roles() != null && !dto.roles().isEmpty()) {
            notice.setRoles(dto.roles().stream()
                    .map(r -> new NoticeRole(r.role(), r.vacancies()))
                    .toList());
        }

        if (dto.requirements() != null) {
            notice.setRequirements(dto.requirements());
        }

        if (dto.documents() != null && !dto.documents().isEmpty()) {
            notice.setDocuments(dto.documents());
        }

        if (dto.quotas() != null) {
            Cota cota = new Cota();
            cota.setVagasPcd(dto.quotas().getVagasPcd());
            cota.setVagasNegros(dto.quotas().getVagasNegros());
            cota.setVagasIndigenas(dto.quotas().getVagasIndigenas());
            cota.setOutrasCotas(dto.quotas().getOutrasCotas());
            notice.setQuotas(cota);
        }

        if (dto.schedule() != null && !dto.schedule().isEmpty()) {
            notice.setSchedule(dto.schedule().stream()
                    .map(s -> new ScheduleItem(s.description(), s.date()))
                    .toList());
        }

        if (dto.pdf() != null && !dto.pdf().isEmpty()) {
            String pdfUrl = uploadFile(dto.pdf());
            notice.setPdfUrl(pdfUrl);
        }

        notice = noticeRepository.save(notice);
        return NoticeMapper.toDto(notice);
    }

    private String uploadFile(MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            var uploadResult = localStorageService.salvar(file);
            var linkCru = baseUrl + "/publicos/" + uploadResult;
            return linkCru.replaceAll("\\s+", "_");
        }
        return null;
    }
}
