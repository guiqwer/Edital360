package com.ifce.edital360.model.isencao;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "arquivos_isencao")
public class ArquivosIsencao {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_isencao_id", nullable = false)
    private PedidoIsencao pedidoIsencao;

    @NotNull
    @Column(name = "nome_original", nullable = false)
    private String nomeOriginalArquivo;

    @NotNull
    @Column(name = "caminho_armazenamento", nullable = false)
    private String caminhoArmazenamento;

    @Column(name = "tamanho_bytes", nullable = false)
    private long tamanho;

    @Column(name = "mime_type", length = 120)
    private String mimeType;

    @Column(name = "upado_em", nullable = false, updatable = false)
    private Instant upadoEm;

    @PrePersist
    protected void prePersist() {
        this.upadoEm = Instant.now();
    }

    // Getters e setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public PedidoIsencao getPedidoIsencao() { return pedidoIsencao; }

    public void setPedidoIsencao(PedidoIsencao pedidoIsencao) { this.pedidoIsencao = pedidoIsencao; }

    public String getNomeOriginalArquivo() { return nomeOriginalArquivo; }
    public void setNomeOriginalArquivo(String nomeOriginalArquivo) { this.nomeOriginalArquivo = nomeOriginalArquivo; }

    public String getCaminhoArmazenamento() { return caminhoArmazenamento; }
    public void setCaminhoArmazenamento(String caminhoArmazenamento) { this.caminhoArmazenamento = caminhoArmazenamento; }

    public long getTamanho() { return tamanho; }
    public void setTamanho(long tamanho) { this.tamanho = tamanho; }

    public String getMimeType() { return mimeType; }
    public void setMimeType(String mimeType) { this.mimeType = mimeType; }

    public Instant getUpadoEm() { return upadoEm; }
    public void setUpadoEm(Instant upadoEm) { this.upadoEm = upadoEm; }
}