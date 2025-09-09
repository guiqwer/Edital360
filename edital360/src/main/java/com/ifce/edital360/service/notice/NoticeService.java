package com.ifce.edital360.service.notice;

import com.ifce.edital360.dto.edital.NoticeCreateDto;
import com.ifce.edital360.dto.edital.NoticeResponseDto;
import com.ifce.edital360.mapper.NoticeMapper;
import com.ifce.edital360.model.edital.Notice;
import com.ifce.edital360.repository.NoticeRepository;
import com.ifce.edital360.service.localStorage.LocalStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
}
