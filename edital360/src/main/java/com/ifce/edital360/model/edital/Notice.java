package com.ifce.edital360.model.edital;


import com.ifce.edital360.model.enums.StatusNotice;
import com.ifce.edital360.model.isencao.PedidoIsencao;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
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
    private LocalDate initialDate;
    private LocalDate endDate;
    private LocalDate createdAt;
    private LocalDate examDate;


    @ElementCollection
    @CollectionTable(name = "notice_phases", joinColumns = @JoinColumn(name = "notice_id"))
    private List<Phase> phases = new ArrayList<>();

    @ElementCollection
    @CollectionTable(
            name = "notice_roles",
            joinColumns = @JoinColumn(name = "notice_id")
    )
    private List<NoticeRole> roles = new ArrayList<>();

    private Requirement requirements;

    @ElementCollection
    private List<String> documents = new ArrayList<>();

    private Cota quotas;
    private BigDecimal subscription;
    private String pdfUrl;

    @ElementCollection
    @CollectionTable(name = "notice_schedule", joinColumns = @JoinColumn(name = "notice_id"))
    private List<ScheduleItem> schedule = new ArrayList<>();

    //----------------------------Isencao----------------------

    @Embedded
    private Exemption exemption;

    public Exemption getExemption() {
        return exemption;
    }

    public void setExemption(Exemption exemption) {
        this.exemption = exemption;
    }

    @OneToMany(
            mappedBy = "notice",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PedidoIsencao> exemptionRequests = new ArrayList<>();

    public List<PedidoIsencao> getExemptionRequests() {
        return exemptionRequests;
    }

    public void setExemptionRequests(List<PedidoIsencao> exemptionRequests) {
        this.exemptionRequests = exemptionRequests;
    }

    //----------------------------------------------------------

    @Enumerated(EnumType.STRING)
    private StatusNotice statusNotice;

    private boolean statusManual;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        if(this.statusNotice == null) {
            this.statusNotice = StatusNotice.PUBLICADO;
        }
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
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

    public Cota getQuotas() {
        return quotas;
    }

    public void setQuotas(Cota quotas) {
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

    public StatusNotice getStatusNotice() {
        return statusNotice;
    }

    public void setStatusNotice(StatusNotice statusNotice) {
        this.statusNotice = statusNotice;
    }

    public boolean isStatusManual() {
        return statusManual;
    }

    public void setStatusManual(boolean statusManual) {
        this.statusManual = statusManual;
    }

    public Notice(UUID id, String title, String description, BigDecimal remuneration, LocalDate initialDate, LocalDate endDate, LocalDate createdAt, LocalDate examDate, List<Phase> phases, List<NoticeRole> roles, Requirement requirements, List<String> documents, Cota quotas, BigDecimal subscription, String pdfUrl, List<ScheduleItem> schedule, Exemption exemption, List<PedidoIsencao> exemptionRequests, StatusNotice statusNotice, boolean statusManual) {
        this.id = id;
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
        this.quotas = quotas;
        this.subscription = subscription;
        this.pdfUrl = pdfUrl;
        this.schedule = schedule;
        this.exemption = exemption;
        this.exemptionRequests = exemptionRequests;
        this.statusNotice = statusNotice;
        this.statusManual = statusManual;
    }

    public Notice() {
    }
}
