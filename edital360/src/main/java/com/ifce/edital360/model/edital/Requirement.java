package com.ifce.edital360.model.edital;

import com.ifce.edital360.model.enums.RequirementType;
import jakarta.persistence.Embeddable;

@Embeddable
public class Requirement {
    private RequirementType requirementType;
    private Integer minimumAge;
    private Integer maximumAge;

    public Requirement(RequirementType requirementType, Integer minimumAge, Integer maximumAge) {
        this.requirementType = requirementType;
        this.minimumAge = minimumAge;
        this.maximumAge = maximumAge;
    }

    public Requirement() {
    }

    public RequirementType getRequirementType() {
        return requirementType;
    }

    public void setRequirementType(RequirementType requirementType) {
        this.requirementType = requirementType;
    }

    public Integer getMinimumAge() {
        return minimumAge;
    }

    public void setMinimumAge(Integer minimumAge) {
        this.minimumAge = minimumAge;
    }

    public Integer getMaximumAge() {
        return maximumAge;
    }

    public void setMaximumAge(Integer maximumAge) {
        this.maximumAge = maximumAge;
    }
}

