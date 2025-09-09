package com.ifce.edital360.controller.edital;

import com.ifce.edital360.dto.edital.NoticeCreateDto;
import com.ifce.edital360.dto.edital.NoticeResponseDto;
import com.ifce.edital360.dto.edital.NoticeUpdateDto;
import com.ifce.edital360.service.notice.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/editais")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping(value = "/cadastrar", consumes = "multipart/form-data")
    @Operation(summary = "Cadastra um novo edital")
    public ResponseEntity<NoticeResponseDto> cadastrarEdital(@Valid @ModelAttribute NoticeCreateDto dto) throws IOException {
        NoticeResponseDto response = noticeService.createNotice(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping(value = "/obter")
    @Operation(summary = "Obtém todos os editais cadastrados")
    public ResponseEntity<List<NoticeResponseDto>> obter() {
        List<NoticeResponseDto> response = noticeService.getAll();
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
}
