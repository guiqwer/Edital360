package com.ifce.edital360.dto.responsegenerico;

import java.time.LocalDateTime;

public record ApiResponse(
        String status,
        String message,
        LocalDateTime timestamp
) {
    public ApiResponse(String status, String message) {
        this(status, message, LocalDateTime.now());
    }
}