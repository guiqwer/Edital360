package com.ifce.edital360.model.edital;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;

import java.time.LocalDate;
import java.util.List;

@Embeddable
public class Exemption {
    private LocalDate exemptionStartDate;
    private LocalDate exemptionEndDate;

    @ElementCollection
    private List<String> eligibleCategories;

    @Column(length = 1000)
    private String documentationDescription;

    private Boolean enabled = true;
    // Construtores
    public Exemption() {}

    public Exemption(LocalDate exemptionStartDate, LocalDate exemptionEndDate,
                     List<String> eligibleCategories, String documentationDescription) {
        this.exemptionStartDate = exemptionStartDate;
        this.exemptionEndDate = exemptionEndDate;
        this.eligibleCategories = eligibleCategories;
        this.documentationDescription = documentationDescription;
    }

    // Getters e Setters


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

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

    //metodos de verificação

    /**
     * Verifica APENAS se a data atual está dentro do intervalo de início e fim.
     * @return true se o período estiver aberto, false caso contrário.
     */
    public boolean isPeriodOpen() {
        // Garante que as datas não são nulas antes de comparar
        if (this.exemptionStartDate == null || this.exemptionEndDate == null) {
            return false;
        }
        final LocalDate today = LocalDate.now();
        return !today.isBefore(exemptionStartDate) && !today.isAfter(exemptionEndDate);
    }


    /**
     * Método de verificação completo: A isenção está habilitada E o período está aberto?
     * É ESTE método que você deve usar na sua camada de Serviço.
     * @return true se a isenção estiver totalmente ativa.
     */
    public boolean isRequestable() {
        return this.enabled && isPeriodOpen();
    }
}