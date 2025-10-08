package com.ifce.edital360.dto.isencao;

import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;
import java.util.List;

public class ExemptionDto {

    @FutureOrPresent(message = "A data inicial da isenção deve ser posterior a data atual.")
    private LocalDate exemptionStartDate;

    @FutureOrPresent(message = "A data final da isenção deve ser posterior a data atual.")
    private LocalDate exemptionEndDate;
    private List<String> eligibleCategories;
    private String documentationDescription;

    // Construtor padrão (sem argumentos)
    public ExemptionDto() {
    }

    // Construtor com todos os argumentos
    public ExemptionDto(LocalDate exemptionStartDate, LocalDate exemptionEndDate, List<String> eligibleCategories, String documentationDescription) {
        this.exemptionStartDate = exemptionStartDate;
        this.exemptionEndDate = exemptionEndDate;
        this.eligibleCategories = eligibleCategories;
        this.documentationDescription = documentationDescription;
    }

    // Getters e Setters
    public LocalDate getExemptionStartDate() {
        return exemptionStartDate;
    }

    public void setExemptionStartDate(LocalDate exemptionStartDate) {
        this.exemptionStartDate = exemptionStartDate;
    }

    public LocalDate getExemptionEndDate() {
        return exemptionEndDate;
    }

    public void setExemptionEndDate(LocalDate exemptionEndDate) {
        this.exemptionEndDate = exemptionEndDate;
    }

    public List<String> getEligibleCategories() {
        return eligibleCategories;
    }

    public void setEligibleCategories(List<String> eligibleCategories) {
        this.eligibleCategories = eligibleCategories;
    }

    public String getDocumentationDescription() {
        return documentationDescription;
    }

    public void setDocumentationDescription(String documentationDescription) {
        this.documentationDescription = documentationDescription;
    }
}