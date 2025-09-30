package com.ifce.edital360.dto.edital;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.util.Objects;

public class NoticeRoleDto {

    @NotBlank
    private String role;

    @PositiveOrZero
    private Integer vacancies;

    // Construtor vazio (obrigatório para o Spring)
    public NoticeRoleDto() {
    }

    // Construtor com todos os campos (opcional)
    public NoticeRoleDto(String role, Integer vacancies) {
        this.role = role;
        this.vacancies = vacancies;
    }

    // Getters e Setters
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
}