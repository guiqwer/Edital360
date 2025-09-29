package com.ifce.edital360.dto.edital;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Objects;

public class ScheduleItemDto {

    private String description;

    @NotNull
    private LocalDateTime date;

    // Construtor vazio, necessário para frameworks como o Spring
    public ScheduleItemDto() {
    }

    // Construtor com todos os campos para conveniência
    public ScheduleItemDto(String description, LocalDateTime date) {
        this.description = description;
        this.date = date;
    }

    // Getters e Setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

}