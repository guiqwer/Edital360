package com.ifce.edital360.dto.edital;

import com.ifce.edital360.model.edital.Requirement;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class NoticeUpdateDto {

    private String title;
    private String description;

    @Positive
    private BigDecimal remuneration;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate initialDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate examDate;

    private List<PhaseDto> phases;
    private List<NoticeRoleDto> roles;
    private Requirement requirements;
    private List<String> documents;
    private CotaDto quotas;

    @Positive
    private BigDecimal subscription;

    private MultipartFile pdf;
    private List<ScheduleItemDto> schedule;

    // Construtor vazio obrigatório para Spring MVC
    public NoticeUpdateDto() {
    }

    // Getters e Setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getRemuneration() { return remuneration; }
    public void setRemuneration(BigDecimal remuneration) { this.remuneration = remuneration; }

    public LocalDate getInitialDate() { return initialDate; }
    public void setInitialDate(LocalDate initialDate) { this.initialDate = initialDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public LocalDate getExamDate() { return examDate; }
    public void setExamDate(LocalDate examDate) { this.examDate = examDate; }

    public List<PhaseDto> getPhases() { return phases; }
    public void setPhases(List<PhaseDto> phases) { this.phases = phases; }

    public List<NoticeRoleDto> getRoles() { return roles; }
    public void setRoles(List<NoticeRoleDto> roles) { this.roles = roles; }

    public Requirement getRequirements() { return requirements; }
    public void setRequirements(Requirement requirements) { this.requirements = requirements; }

    public List<String> getDocuments() { return documents; }
    public void setDocuments(List<String> documents) { this.documents = documents; }

    public CotaDto getQuotas() { return quotas; }
    public void setQuotas(CotaDto quotas) { this.quotas = quotas; }

    public BigDecimal getSubscription() { return subscription; }
    public void setSubscription(BigDecimal subscription) { this.subscription = subscription; }

    public MultipartFile getPdf() { return pdf; }
    public void setPdf(MultipartFile pdf) { this.pdf = pdf; }

    public List<ScheduleItemDto> getSchedule() { return schedule; }
    public void setSchedule(List<ScheduleItemDto> schedule) { this.schedule = schedule; }

}
