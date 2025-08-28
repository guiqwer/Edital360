package com.ifce.edital360.dto.notices;

import org.springframework.web.multipart.MultipartFile;

public record AnnouncementsDto(
        MultipartFile file
) {
}
