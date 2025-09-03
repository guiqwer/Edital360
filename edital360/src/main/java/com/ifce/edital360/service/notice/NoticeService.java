package com.ifce.edital360.service.notice;

import com.ifce.edital360.dto.notices.NoticeCreateDto;
import com.ifce.edital360.dto.notices.NoticeResponseDto;
import com.ifce.edital360.mapper.NoticeMapper;
import com.ifce.edital360.model.notices.Notice;
import com.ifce.edital360.repository.ExternalLinksRepository;
import com.ifce.edital360.repository.NoticeRepository;
import com.ifce.edital360.service.localStorage.LocalStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Autowired
    private ExternalLinksRepository externalLinksRepository;

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

        List<String> announcementsUrls = null;
        if(dto.announcements() != null) {
            announcementsUrls = dto.announcements().stream()
                    .filter(a -> a.file() != null && !a.file().isEmpty())
                    .map(a -> {
                        try {
                            return localStorageService.salvar(a.file());
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .toList();
        }

        Notice notice = NoticeMapper.toEntity(dto, pdfUrl, announcementsUrls);

        notice = noticeRepository.save(notice);

        return NoticeMapper.toDto(notice);
    }
}
