package com.ifce.edital360.model.enums;

public enum StatusNotice {
    PUBLICADO, //todos vão ser adicionados com esse
    PEDIDO_ISENCAO, //vai ser alterado por scheduler
    EM_ANALISE, // vai ser alterado por scheduler
    INSCRICOES_ABERTAS, //vai ser alterado por scheduler
    INSCRICOES_ENCERRADAS, //vai ser alterado por scheduler
    RESULTADO_PRELIMINAR, //vai ser alterado manualmente
    RESULTADO_FINAL, // vai ser alterado manualmente
    ENCERRADO, //vai ser alterado manualmente
    CANCELADO //vai ser alterado manualmente
}
