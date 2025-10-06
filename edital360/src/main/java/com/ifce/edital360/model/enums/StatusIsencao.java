package com.ifce.edital360.model.enums;

public enum StatusIsencao {

    PENDENTE("Pendente", "O pedido foi enviado pelo candidato e aguarda o início da análise."),
    EM_ANALISE("Em Análise", "O pedido está sendo avaliado pela comissão responsável."),
    DEFERIDO("Deferido", "O pedido de isenção foi aceito com sucesso."),
    INDEFERIDO("Indeferido", "O pedido de isenção foi negado.");

    private final String nomeAmigavel;
    private final String descricao;

    StatusIsencao(String nomeAmigavel, String descricao) {
        this.nomeAmigavel = nomeAmigavel;
        this.descricao = descricao;
    }

    public String getNomeAmigavel() {
        return nomeAmigavel;
    }

    public String getDescricao() {
        return descricao;
    }
}