package com.ifce.edital360.dto.notices;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

public record ExternalLinksDto(
        UUID id,
        @NotBlank @URL String url,
        LocalDateTime createdAt
) {
}
