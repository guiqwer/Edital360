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

    private boolean enabled = true;
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
}