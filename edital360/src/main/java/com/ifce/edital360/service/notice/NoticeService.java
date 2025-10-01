package com.ifce.edital360.service.notice;

import com.ifce.edital360.dto.edital.NoticeCreateDto;
import com.ifce.edital360.dto.edital.NoticeResponseDto;
import com.ifce.edital360.dto.edital.NoticeUpdateDto;
import com.ifce.edital360.dto.isencao.ExemptionDto;
import com.ifce.edital360.dto.isencao.ExemptionSummaryDto;
import com.ifce.edital360.dto.isencao.PedidoIsencaoResponseDTO;
import com.ifce.edital360.mapper.NoticeMapper;
import com.ifce.edital360.model.edital.*;
import com.ifce.edital360.model.isencao.PedidoIsencao;
import com.ifce.edital360.repository.NoticeRepository;
import com.ifce.edital360.service.localStorage.LocalStorageService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private void updateExemptionFields(Notice notice, NoticeUpdateDto dto) {
        if (dto.getExemption() != null) {
            ExemptionDto e = dto.getExemption();
            Exemption exemption = notice.getExemption();
            if (exemption == null) {
                exemption = new Exemption();
            }

            if (e.getExemptionStartDate() != null) exemption.setExemptionStartDate(e.getExemptionStartDate());
            if (e.getExemptionEndDate()!= null) exemption.setExemptionEndDate(e.getExemptionEndDate());
            if (e.getEligibleCategories() != null) exemption.setEligibleCategories(e.getEligibleCategories());
            if (e.getDocumentationDescription() != null) exemption.setDocumentationDescription(e.getDocumentationDescription());

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
        // 1. Busca o edital pelo ID. Se não existir, lança uma exceção.
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new EntityNotFoundException("Edital não encontrado com ID: " + noticeId));

        // 2. Pega o objeto de isenção associado ao edital.
        Exemption exemption = notice.getExemption();

        // 3. Verifica se o edital realmente possui uma isenção.
        if (exemption == null) {
            throw new EntityNotFoundException("Não há informações de isenção para o edital com ID: " + noticeId);
        }

        // 4. Mapeia a entidade para o DTO de resumo e retorna.
        return new ExemptionSummaryDto(
                notice.getId(), // Ou exemption.getId(), dependendo do que você quer expor
                exemption.getExemptionStartDate(),
                exemption.getExemptionEndDate(),
                exemption.getEligibleCategories(),
                exemption.getDocumentationDescription()
        );
    }

    public List<PedidoIsencaoResponseDTO> getPedidosByNoticeId(UUID noticeId) {
        // 1. Encontra o edital ou lança uma exceção
        Notice notice = noticeRepository.findById(noticeId)
                .orElseThrow(() -> new EntityNotFoundException("Edital não encontrado com ID: " + noticeId));

        // 2. USA A NOVA LISTA! É aqui que a mágica acontece.
        // Pega a lista de pedidos que já está carregada no edital
        List<PedidoIsencao> pedidos = notice.getExemptionRequests(); // Ou getPedidosIsencao()

        // 3. Converte a lista de entidades para uma lista de DTOs
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

        return notice.getExemption(); // Retorna o objeto embutido
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
