package com.ifce.edital360.model.edital;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class ScheduleItem {
    private String description;
    private LocalDateTime date;

    public ScheduleItem(String description, Object o, LocalDateTime date) {
    }

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

    public ScheduleItem() {
    }

    public ScheduleItem(String description, LocalDateTime date) {
        this.description = description;
        this.date = date;
    }
}

