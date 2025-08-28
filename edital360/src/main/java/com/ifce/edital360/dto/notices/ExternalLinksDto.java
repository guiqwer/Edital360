package com.ifce.edital360.dto.notices;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

public record ExternalLinksDto(
        @NotBlank String url
) {
}
