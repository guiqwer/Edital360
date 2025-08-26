package com.ifce.edital360.model.notices;


import jakarta.persistence.*;

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
    private String numVacancies;
    private Float remuneration;
    private LocalDateTime initialDate;
    private LocalDateTime endDate;

    @ElementCollection
    private List<String> roles;

    @OneToMany(mappedBy = "notice", cascade = CascadeType.ALL,  orphanRemoval = true)
    private List<ExternalLinks> externalLinks;
    private String pdfUrl;

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

    public String getNumVacancies() {
        return numVacancies;
    }

    public void setNumVacancies(String numVacancies) {
        this.numVacancies = numVacancies;
    }

    public Float getRemuneration() {
        return remuneration;
    }

    public void setRemuneration(Float remuneration) {
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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public List<ExternalLinks> getExternalLinks() {
        return externalLinks;
    }

    public void setExternalLinks(List<ExternalLinks> externalLinks) {
        this.externalLinks = externalLinks;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public Notice(String title, Float remuneration, String numVacancies, LocalDateTime initialDate, LocalDateTime endDate, List<String> roles, List<ExternalLinks> externalLinks, String pdfUrl) {
        this.title = title;
        this.remuneration = remuneration;
        this.numVacancies = numVacancies;
        this.initialDate = initialDate;
        this.endDate = endDate;
        this.roles = roles;
        this.externalLinks = externalLinks;
        this.pdfUrl = pdfUrl;
    }

    public Notice() {
    }
}
