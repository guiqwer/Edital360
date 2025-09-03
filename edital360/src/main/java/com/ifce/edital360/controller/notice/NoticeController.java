package com.ifce.edital360.controller.notice;

import com.ifce.edital360.dto.notices.NoticeCreateDto;
import com.ifce.edital360.dto.notices.NoticeResponseDto;
import com.ifce.edital360.service.notice.NoticeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping("/cadastrar")
    public ResponseEntity<NoticeResponseDto> cadastrarEdital(@Valid @ModelAttribute NoticeCreateDto dto) throws IOException {
        NoticeResponseDto response = noticeService.createNotice(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
