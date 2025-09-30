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

        if(dto.getPdf()!= null && !dto.getPdf().isEmpty()) {
            var uploadResult = localStorageService.salvar(dto.getPdf());
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

        if (dto.getTitle() != null) notice.setTitle(dto.getTitle());
        if (dto.getDescription()!= null) notice.setDescription(dto.getDescription());
        if (dto.getRemuneration() != null) notice.setRemuneration(dto.getRemuneration());
        if (dto.getInitialDate() != null) notice.setInitialDate(dto.getInitialDate());
        if (dto.getEndDate()!= null) notice.setEndDate(dto.getEndDate());
        if (dto.getExamDate() != null) notice.setExamDate(dto.getExamDate());
        if (dto.getSubscription() != null) notice.setSubscription(dto.getSubscription());

        if (dto.getPhases()!= null && !dto.getPhases().isEmpty()) {
            notice.setPhases(dto.getPhases().stream()
                    .map(p -> new Phase(p.getOrder(), p.getExam()))
                    .toList());
        }

        if (dto.getRoles() != null && !dto.getRoles() .isEmpty()) {
            notice.setRoles(dto.getRoles() .stream()
                    .map(r -> new NoticeRole(r.getRole(), r.getVacancies()))
                    .toList());
        }

        if (dto.getRequirements() != null) {
            notice.setRequirements(dto.getRequirements());
        }

        if (dto.getDocuments() != null && !dto.getDocuments().isEmpty()) {
            notice.setDocuments(dto.getDocuments());
        }

        if (dto.getQuotas() != null) {
            Cota cota = new Cota();
            cota.setVagasPcd(dto.getQuotas().getVagasPcd());
            cota.setVagasNegros(dto.getQuotas().getVagasNegros());
            cota.setVagasIndigenas(dto.getQuotas().getVagasIndigenas());
            cota.setOutrasCotas(dto.getQuotas().getOutrasCotas());
            notice.setQuotas(cota);
        }

        if (dto.getSchedule() != null && !dto.getSchedule().isEmpty()) {
            notice.setSchedule(dto.getSchedule().stream()
                    .map(s -> new ScheduleItem(s.getDescription(),s.getDate()))
                    .toList());
        }

        if (dto.getPdf() != null && !dto.getPdf().isEmpty()) {
            String pdfUrl = uploadFile(dto.getPdf());
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
