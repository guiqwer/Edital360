package com.ifce.edital360.model.notices;

import jakarta.persistence.Embeddable;

@Embeddable
public class NoticeRole {
    private String role;
    private Integer vacancies;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getVacancies() {
        return vacancies;
    }

    public void setVacancies(Integer vacancies) {
        this.vacancies = vacancies;
    }

    public NoticeRole() {
    }

    public NoticeRole(String role, Integer vacancies) {
        this.role = role;
        this.vacancies = vacancies;
    }
}
