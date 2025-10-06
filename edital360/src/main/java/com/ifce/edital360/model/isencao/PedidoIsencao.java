package com.ifce.edital360.model.isencao;

import com.ifce.edital360.model.edital.Notice;
import com.ifce.edital360.model.enums.StatusIsencao;
import com.ifce.edital360.model.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "pedido_isencao",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"notice_id", "user_id"})})
public class PedidoIsencao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "notice_id", nullable = false)
    private Notice notice;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User aplicante;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusIsencao status = StatusIsencao.PENDENTE;

    @Column(name = "enviado_em", nullable = false, updatable = false)
    private Instant enviandoEm;

    @Column(name = "revisado_em")
    private Instant revisadoEm;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "revisado_por", nullable = true)
    private User revisadoPor;

    @Column(name = "comentario_revisor", length = 2000)
    private String comentarioRevisador;

    @OneToMany(
            mappedBy = "pedidoIsencao",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private List<ArquivosIsencao> arquivos = new ArrayList<>();

    @PrePersist
    protected void prePersist() {
        this.enviandoEm = Instant.now();
    }

    // Getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Notice getNotice() { return notice; }
    public void setNotice(Notice notice) { this.notice = notice; }

    public User getAplicante() { return aplicante; }
    public void setAplicante(User aplicante) { // <-- CORRIGIDO
        this.aplicante = aplicante;
    }

    public StatusIsencao getStatus() { return status; }
    public void setStatus(StatusIsencao status) { this.status = status; }

    public Instant getEnviandoEm() { return enviandoEm; }
    public void setEnviandoEm(Instant enviandoEm) { this.enviandoEm = enviandoEm; }

    public Instant getRevisadoEm() { return revisadoEm; }
    public void setRevisadoEm(Instant revisadoEm) { this.revisadoEm = revisadoEm; }

    public User getRevisadoPor() { return revisadoPor; }
    public void setRevisadoPor(User revisadoPor) { this.revisadoPor = revisadoPor; }

    public String getComentarioRevisador() { return comentarioRevisador; }
    public void setComentarioRevisador(String comentarioRevisador) { this.comentarioRevisador = comentarioRevisador; }

    public List<ArquivosIsencao> getArquivos() { return arquivos; }
    public void setArquivos(List<ArquivosIsencao> arquivos) { this.arquivos = arquivos; }

    // Helpers para relação bidirecional
    public void addArquivo(ArquivosIsencao arquivo) {
        arquivos.add(arquivo);
        arquivo.setPedidoIsencao(this);
    }

    public void removeArquivo(ArquivosIsencao arquivo) {
        arquivos.remove(arquivo);
        arquivo.setPedidoIsencao(null);
    }
}