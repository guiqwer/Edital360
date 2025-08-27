package com.ifce.edital360.model.notices;

import com.ifce.edital360.model.enums.Exam;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class Phase {
    private Integer order;
    @Enumerated(EnumType.STRING)
    private Exam exam;

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

    public Phase() {
    }

    public Phase(Integer order, Exam exam) {
        this.order = order;
        this.exam = exam;
    }
}
