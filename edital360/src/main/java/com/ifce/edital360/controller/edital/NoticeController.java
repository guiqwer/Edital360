package com.ifce.edital360.controller.edital;

import com.ifce.edital360.dto.edital.NoticeCreateDto;
import com.ifce.edital360.dto.edital.NoticeResponseDto;
import com.ifce.edital360.dto.edital.NoticeUpdateDto;
import com.ifce.edital360.dto.isencao.ExemptionSummaryDto;
import com.ifce.edital360.dto.isencao.PedidoIsencaoResponseDTO;
import com.ifce.edital360.model.edital.Exemption;
import com.ifce.edital360.model.enums.StatusNotice;
import com.ifce.edital360.service.notice.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/editais")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @GetMapping("/isencoes/ativas")
    public List<ExemptionSummaryDto> getActiveExemptions() {
        return noticeService.getActiveExemptions();
    }

    @GetMapping("/{id}/pedidos-isencao")
    @Operation(summary = "Obtém todos os pedidos de isenção de um edital específico")
    public ResponseEntity<List<PedidoIsencaoResponseDTO>> getPedidosPorEdital(@PathVariable UUID id) {
        List<PedidoIsencaoResponseDTO> response = noticeService.getPedidosByNoticeId(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/isencao")
    @Operation(summary = "Obtém as regras de isenção de um edital específico")
    public ResponseEntity<Exemption> getRegrasIsencaoPorEdital(@PathVariable("id") UUID id) {
        Exemption exemptionRules = noticeService.getExemptionRulesByNoticeId(id);
        return ResponseEntity.ok(exemptionRules);
    }

    @PostMapping(value = "/cadastrar", consumes = "multipart/form-data")
    @Operation(summary = "Cadastra um novo edital")
    public ResponseEntity<NoticeResponseDto> cadastrarEdital(@Valid @ModelAttribute NoticeCreateDto dto) throws IOException {
        NoticeResponseDto response = noticeService.createNotice(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    @Operation(summary = "Busca todos os avisos de forma paginada")
    @Parameters({
            @Parameter(name = "page", description = "Número da página que você deseja obter (0..N)",
                    example = "0", schema = @Schema(type = "integer")),
            @Parameter(name = "size", description = "Quantidade de elementos por página.",
                    example = "10", schema = @Schema(type = "integer")),
            @Parameter(name = "sort", description = "Critério de ordenação no formato: propriedade,(asc|desc). " +
                    "A ordenação padrão é ascendente. Múltiplos critérios são suportados.",
                    example = "initialDate,desc", schema = @Schema(type = "string"))
    })
    @GetMapping(value = "/obter")
    public ResponseEntity<Page<NoticeResponseDto>> obter(@Parameter(hidden = true) Pageable pageable, @Parameter(hidden = false) StatusNotice statusNotice) {
        Page<NoticeResponseDto> response = noticeService.getAll(statusNotice, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/obter/{id}")
    @Operation(summary = "Obtém um edital por id")
    public ResponseEntity<NoticeResponseDto> obterPorId(@PathVariable UUID id) {
        NoticeResponseDto response = noticeService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/atualizar/{id}", consumes = "multipart/form-data")
    @Operation(summary = "Atualiza um edital por id")
    public ResponseEntity<NoticeResponseDto> updateNotice(
            @PathVariable UUID id,
            @Valid @ModelAttribute NoticeUpdateDto dto
    ) throws IOException {
        NoticeResponseDto updated = noticeService.update(id, dto);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @PatchMapping(value = "/atualizar/{id}")
    @Operation(summary = "Atualiza o status de um edital pelo id")
    public ResponseEntity<NoticeResponseDto> updateNoticeStatus(
            @PathVariable UUID id,
            @ModelAttribute StatusNotice statusNotice
            ) throws IOException {
        NoticeResponseDto updated = noticeService.updateStatusNotice(id, statusNotice);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
}
