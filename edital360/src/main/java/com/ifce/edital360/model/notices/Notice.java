package com.ifce.edital360.model.notices;


import com.ifce.edital360.model.enums.Exam;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "notices")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String title;
    private String description;
    private BigDecimal remuneration;
    private LocalDateTime initialDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private LocalDateTime examDate;


    @ElementCollection
    @CollectionTable(name = "notice_phases", joinColumns = @JoinColumn(name = "notice_id"))
    private List<Phase> phases;

    @ElementCollection
    @CollectionTable(
            name = "notice_roles",
            joinColumns = @JoinColumn(name = "notice_id")
    )
    private List<NoticeRole> roles;

    @ElementCollection
    private List<String> requirements;

    @ElementCollection
    private List<String> documents;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<ExternalLinks> externalLinks;

    @OneToMany(mappedBy = "notice",  cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<Announcements> announcements;

    @ElementCollection
    private List<String> quotas;
    private BigDecimal subscription;
    private String pdfUrl;

    @ElementCollection
    @CollectionTable(name = "notice_schedule", joinColumns = @JoinColumn(name = "notice_id"))
    private List<ScheduleItem> schedule;


    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public LocalDateTime getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(LocalDateTime initialDate) {
        this.initialDate = initialDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDateTime examDate) {
        this.examDate = examDate;
    }

    public List<Phase> getPhases() {
        return phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }

    public List<NoticeRole> getRoles() {
        return roles;
    }

    public void setRoles(List<NoticeRole> roles) {
        this.roles = roles;
    }

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    public List<ExternalLinks> getExternalLinks() {
        return externalLinks;
    }

    public void setExternalLinks(List<ExternalLinks> externalLinks) {
        this.externalLinks = externalLinks;
    }

    public List<Announcements> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcements> announcements) {
        this.announcements = announcements;
    }

    public List<String> getQuotas() {
        return quotas;
    }

    public void setQuotas(List<String> quotas) {
        this.quotas = quotas;
    }

    public BigDecimal getSubscription() {
        return subscription;
    }

    public void setSubscription(BigDecimal subscription) {
        this.subscription = subscription;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public List<ScheduleItem> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<ScheduleItem> schedule) {
        this.schedule = schedule;
    }

    public Notice(String title, String description, BigDecimal remuneration, LocalDateTime initialDate, LocalDateTime endDate, LocalDateTime createdAt, LocalDateTime examDate, List<Phase> phases, List<NoticeRole> roles, List<String> requirements, List<String> documents, List<ExternalLinks> externalLinks, List<Announcements> announcements, List<String> quotas, BigDecimal subscription, String pdfUrl, List<ScheduleItem> schedule) {
        this.title = title;
        this.description = description;
        this.remuneration = remuneration;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.examDate = examDate;
        this.phases = phases;
        this.roles = roles;
        this.requirements = requirements;
        this.documents = documents;
        this.externalLinks = externalLinks;
        this.announcements = announcements;
        this.quotas = quotas;
        this.subscription = subscription;
        this.pdfUrl = pdfUrl;
        this.schedule = schedule;
    }

    public Notice() {
    }
}
