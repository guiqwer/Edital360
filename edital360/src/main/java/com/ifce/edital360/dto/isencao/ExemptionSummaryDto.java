package com.ifce.edital360.dto.isencao;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public final class ExemptionSummaryDto {
    private final UUID id;
    private final LocalDate exemptionStartDate;
    private final LocalDate exemptionEndDate;
    private final List<String> eligibleCategories;
    private final String documentationDescription;

    public ExemptionSummaryDto(
            UUID id,
            LocalDate exemptionStartDate,
            LocalDate exemptionEndDate,
            List<String> eligibleCategories,
            String documentationDescription
    ) {
        this.id = id;
        this.exemptionStartDate = exemptionStartDate;
        this.exemptionEndDate = exemptionEndDate;
        this.eligibleCategories = eligibleCategories;
        this.documentationDescription = documentationDescription;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getExemptionStartDate() {
        return exemptionStartDate;
    }

    public LocalDate getExemptionEndDate() {
        return exemptionEndDate;
    }

    public List<String> getEligibleCategories() {
        return eligibleCategories;
    }

    public String getDocumentationDescription() {
        return documentationDescription;
    }

}