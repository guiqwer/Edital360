package com.ifce.edital360.dto.notices;

import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

public record AnnouncementsDto(
        UUID id,
        String url,
        LocalDateTime createdAt,
        MultipartFile file
) {
}
