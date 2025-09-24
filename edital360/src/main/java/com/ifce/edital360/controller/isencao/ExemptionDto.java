package com.ifce.edital360.controller.isencao;

import java.time.LocalDate;
import java.util.List;

public record ExemptionDto(
        LocalDate exemptionStartDate,
        LocalDate exemptionEndDate,
        List<String> eligibleCategories,
        String documentationDescription
) {}