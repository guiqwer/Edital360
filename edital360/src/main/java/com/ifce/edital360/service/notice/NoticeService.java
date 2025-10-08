package com.ifce.edital360.service.notice;

import com.ifce.edital360.dto.edital.NoticeCreateDto;
import com.ifce.edital360.dto.edital.NoticeResponseDto;
import com.ifce.edital360.dto.edital.NoticeUpdateDto;
import com.ifce.edital360.dto.isencao.ExemptionDto;
import com.ifce.edital360.dto.isencao.ExemptionSummaryDto;
import com.ifce.edital360.dto.isencao.PedidoIsencaoResponseDTO;
import com.ifce.edital360.mapper.NoticeMapper;
import com.ifce.edital360.model.edital.*;
import com.ifce.edital360.model.enums.StatusNotice;
import com.ifce.edital360.model.isencao.PedidoIsencao;
import com.ifce.edital360.repository.NoticeRepository;
import com.ifce.edital360.service.localStorage.LocalStorageService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

        if (dto.getPdf() != null && !dto.getPdf().isEmpty()) {
            var uploadResult = localStorageService.salvar(dto.getPdf());
            var linkCru = baseUrl + "/publicos/" + uploadResult;
            pdfUrl = linkCru.replaceAll("\\s+", "_");

        }

        Notice notice = NoticeMapper.toEntity(dto, pdfUrl);

        notice = noticeRepository.save(notice);

        return NoticeMapper.toDto(notice);
    }

    public Page<NoticeResponseDto> getAll(StatusNotice statusNotice, Pageable pageable) {
        Page<Notice> notices;

        if(statusNotice != null) {
            notices = noticeRepository.findByStatusNotice(statusNotice, pageable);
        } else{
            notices = noticeRepository.findAll(pageable);
        }
        return NoticeMapper.toDtoPaginated(notices);
    }

    public NoticeResponseDto getById(UUID id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Edital não encontrado com ID: " + id));
        return NoticeMapper.toDto(notice);
    }

    public NoticeResponseDto update(UUID id, NoticeUpdateDto dto) throws IOException {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Edital não encontrado com ID: " + id));

        if (dto.getTitle() != null) notice.setTitle(dto.getTitle());
        if (dto.getDescription() != null) notice.setDescription(dto.getDescription());
        if (dto.getRemuneration() != null) notice.setRemuneration(dto.getRemuneration());
        if (dto.getInitialDate() != null) notice.setInitialDate(dto.getInitialDate());
        if (dto.getEndDate() != null) notice.setEndDate(dto.getEndDate());
        if (dto.getExamDate() != null) notice.setExamDate(dto.getExamDate());
        if (dto.getSubscription() != null) notice.setSubscription(dto.getSubscription());

        if (dto.getPhases() != null && !dto.getPhases().isEmpty()) {
            notice.setPhases(dto.getPhases().stream()
                    .map(p -> new Phase(p.getOrder(), p.getExam()))
                    .toList());
        }

        if (dto.getRoles() != null && !dto.getRoles().isEmpty()) {
            notice.setRoles(dto.getRoles().stream()
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
                    .map(s -> new ScheduleItem(s.getDescription(), s.getDate()))
                    .toList());
        }

        if (dto.getPdf() != null && !dto.getPdf().isEmpty()) {
            String pdfUrl = uploadFile(dto.getPdf());
            notice.setPdfUrl(pdfUrl);
        }

        notice = noticeRepository.save(notice);
        return NoticeMapper.toDto(notice);
    }

    private void updateExemptionFields(Notice notice, NoticeUpdateDto dto) {
        if (dto.getExemption() != null) {
            ExemptionDto e = dto.getExemption();
            Exemption exemption = notice.getExemption();
            if (exemption == null) {
                exemption = new Exemption();
            }

            if (e.getExemptionStartDate() != null) exemption.setExemptionStartDate(e.getExemptionStartDate());
            if (e.getExemptionEndDate() != null) exemption.setExemptionEndDate(e.getExemptionEndDate());
            if (e.getEligibleCategories() != null) exemption.setEligibleCategories(e.getEligibleCategories());
            if (e.getDocumentationDescription() != null)
                exemption.setDocumentationDescription(e.getDocumentationDescription());

            notice.setExemption(exemption);
        }
    }


    public List<ExemptionSummaryDto> getActiveExemptions() {
        LocalDate today = LocalDate.now();
        List<Notice> notices = noticeRepository.findActiveExemptions(today);

        return notices.stream()
                .map(n -> {
                    var e = n.getExemption();
                    return new ExemptionSummaryDto(
                            n.getId(),
                            e.getExemptionStartDate(),
                            e.getExemptionEndDate(),
                            e.getEligibleCategories(),
                            e.getDocumentationDescription()
                    );
                })
                .collect(Collectors.toList());
    }

    public ExemptionSummaryDto getExemptionByNoticeId(UUID noticeId) {

        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new EntityNotFoundException("Edital não encontrado com ID: " + noticeId));

        Exemption exemption = notice.getExemption();

        if (exemption == null) {
            throw new EntityNotFoundException("Não há informações de isenção para o edital com ID: " + noticeId);
        }

        return new ExemptionSummaryDto(
                notice.getId(),
                exemption.getExemptionStartDate(),
                exemption.getExemptionEndDate(),
                exemption.getEligibleCategories(),
                exemption.getDocumentationDescription()
        );
    }

    public List<PedidoIsencaoResponseDTO> getPedidosByNoticeId(UUID noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new EntityNotFoundException("Edital não encontrado com ID: " + noticeId));

        List<PedidoIsencao> pedidos = notice.getExemptionRequests();

        return pedidos.stream()
                .map(PedidoIsencaoResponseDTO::fromEntity)
                .toList();
    }


    public Exemption getExemptionRulesByNoticeId(UUID noticeId) {
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new EntityNotFoundException("Edital não encontrado: " + noticeId));

        if (notice.getExemption() == null) {
            throw new EntityNotFoundException("Este edital não possui regras de isenção.");
        }

        return notice.getExemption();
    }

    private String uploadFile(MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            var uploadResult = localStorageService.salvar(file);
            var linkCru = baseUrl + "/publicos/" + uploadResult;
            return linkCru.replaceAll("\\s+", "_");
        }
        return null;
    }

    @Transactional
    public void updateStatusNotice() {
        List<Notice> notices = noticeRepository.findAll();
        LocalDate today = LocalDate.now();

        List<Notice> toUpdate = notices.stream()
                .filter(n -> !n.isStatusManual())
                .filter(n -> {
                    StatusNotice newStatus = determineStatus(n, today);
                    return newStatus != n.getStatusNotice();
                })
                .peek(n -> n.setStatusNotice(determineStatus(n, today)))
                .toList();

        noticeRepository.saveAll(toUpdate);
    }

    private StatusNotice determineStatus(Notice notice, LocalDate today) {

        Exemption exemption = notice.getExemption();
        if (exemption != null &&
                exemption.getExemptionStartDate() != null &&
                exemption.getExemptionEndDate() != null) {

            if (!today.isBefore(exemption.getExemptionStartDate()) &&
                    !today.isAfter(exemption.getExemptionEndDate())) {
                return StatusNotice.PEDIDO_ISENCAO;
            }
        }

        if (notice.getInitialDate() != null &&
                notice.getEndDate() != null &&
                !today.isBefore(notice.getInitialDate()) &&
                !today.isAfter(notice.getEndDate())) {
            return StatusNotice.INSCRICOES_ABERTAS;
        }

        if (notice.getEndDate() != null && today.isAfter(notice.getEndDate())) {
            return StatusNotice.INSCRICOES_ENCERRADAS;
        }

        return StatusNotice.PUBLICADO;
    }

    public NoticeResponseDto updateStatusNotice(UUID id, StatusNotice statusNotice) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Edital não encontrado com ID: " + id));

        notice.setStatusNotice(statusNotice);
        notice.setStatusManual(true);
        noticeRepository.save(notice);

        return NoticeMapper.toDto(notice);
    }

}