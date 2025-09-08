package com.ifce.edital360.controller.edital;

import com.ifce.edital360.dto.edital.NoticeCreateDto;
import com.ifce.edital360.dto.edital.NoticeResponseDto;
import com.ifce.edital360.service.notice.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/editais")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping(value = "/cadastrar", consumes = "multipart/form-data")
    @Operation(summary = "Cadastrar novo edital")
    public ResponseEntity<NoticeResponseDto> cadastrarEdital(@Valid @ModelAttribute NoticeCreateDto dto) throws IOException {
        NoticeResponseDto response = noticeService.createNotice(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
