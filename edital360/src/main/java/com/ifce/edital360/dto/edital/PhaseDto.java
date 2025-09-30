package com.ifce.edital360.dto.edital;

import com.ifce.edital360.model.enums.Exam;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.Objects;

public class PhaseDto {

    @Positive
    private Integer order;

    @NotNull
    private Exam exam;

    public PhaseDto() {
    }

    public PhaseDto(Integer order, Exam exam) {
        this.order = order;
        this.exam = exam;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}