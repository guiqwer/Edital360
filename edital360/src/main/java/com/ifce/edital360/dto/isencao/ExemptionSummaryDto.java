package com.ifce.edital360.dto.isencao;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record ExemptionSummaryDto(
        UUID id,
        LocalDate exemptionStartDate,
        LocalDate exemptionEndDate,
        List<String>eligibleCategories,
        String documentationDescription
) {
}
