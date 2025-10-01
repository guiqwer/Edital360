package com.ifce.edital360.dto.isencao;

import com.ifce.edital360.model.isencao.ArquivosIsencao;

import java.util.UUID;

public record ArquivoIsencaoDTO(
        UUID id,
        String nomeOriginalArquivo,
        long tamanho,
        String mimeType
) {
    // Note que não expomos o 'caminhoArmazenamento'
    public static ArquivoIsencaoDTO fromEntity(ArquivosIsencao arquivo) {
        return new ArquivoIsencaoDTO(
                arquivo.getId(),
                arquivo.getNomeOriginalArquivo(),
                arquivo.getTamanho(),
                arquivo.getMimeType()
        );
    }
}
