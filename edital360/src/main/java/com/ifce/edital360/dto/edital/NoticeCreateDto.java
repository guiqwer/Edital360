package com.ifce.edital360.dto.edital;

import com.ifce.edital360.dto.isencao.ExemptionDto;
import com.ifce.edital360.model.edital.Requirement;
import com.ifce.edital360.model.enums.StatusNotice;
import com.ifce.edital360.validation.ValidNoticeDates;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@ValidNoticeDates
public class NoticeCreateDto {

    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @Positive
    private BigDecimal remuneration;

    @NotNull
    @FutureOrPresent(message = "A data inicial da inscrição deve ser posterior a data atual.")
    private LocalDate initialDate;

    @NotNull
    @FutureOrPresent(message = "A data final da inscrição deve ser posterior a data atual.")
    private LocalDate endDate;

    @NotNull
    private LocalDate examDate;

    @NotEmpty
    private List<PhaseDto> phases;

    @NotEmpty
    private List<NoticeRoleDto> roles;

    @NotNull
    private Requirement requirements;

    @NotEmpty
    private List<String> documents;

    private CotaDto quotas;

    @NotNull
    @Positive
    private BigDecimal subscription;

    private MultipartFile pdf;

    private List<ScheduleItemDto> schedule;

    @Valid
    ExemptionDto exemption;


    public ExemptionDto getExemption() {
        return exemption;
    }

    public void setExemption(ExemptionDto exemption) {
        this.exemption = exemption;
    }

    // Construtor vazio obrigatório para Spring
    public NoticeCreateDto() {
    }

    // Getters e Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(BigDecimal remuneration) {
        this.remuneration = remuneration;
    }

    public LocalDate getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDate initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public List<PhaseDto> getPhases() {
        return phases;
    }

    public void setPhases(List<PhaseDto> phases) {
        this.phases = phases;
    }

    public List<NoticeRoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<NoticeRoleDto> roles) {
        this.roles = roles;
    }

    public Requirement getRequirements() {
        return requirements;
    }

    public void setRequirements(Requirement requirements) {
        this.requirements = requirements;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public CotaDto getQuotas() {
        return quotas;
    }

    public void setQuotas(CotaDto quotas) {
        this.quotas = quotas;
    }

    public BigDecimal getSubscription() {
        return subscription;
    }

    public void setSubscription(BigDecimal subscription) {
        this.subscription = subscription;
    }

    public MultipartFile getPdf() {
        return pdf;
    }

    public void setPdf(MultipartFile pdf) {
        this.pdf = pdf;
    }

    public List<ScheduleItemDto> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleItemDto> schedule) {
        this.schedule = schedule;
    }
}
