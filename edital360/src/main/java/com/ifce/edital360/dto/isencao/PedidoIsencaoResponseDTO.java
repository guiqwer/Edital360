package com.ifce.edital360.dto.isencao;

import com.ifce.edital360.dto.isencao.ArquivoIsencaoDTO;
import com.ifce.edital360.model.enums.StatusIsencao;
import com.ifce.edital360.model.isencao.PedidoIsencao;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record PedidoIsencaoResponseDTO(
        UUID id,
        StatusIsencao status,
        Instant enviadoEm,
        UUID noticeId,
        String noticeTitle, // Exemplo de como "aplainar" os dados
        UUID aplicanteId,
        List<ArquivoIsencaoDTO> arquivos
) {
    // Padrão "Mapper" simples para converter da Entidade para o DTO
    public static PedidoIsencaoResponseDTO fromEntity(PedidoIsencao pedido) {
        List<ArquivoIsencaoDTO> arquivosDTO = pedido.getArquivos().stream()
                .map(ArquivoIsencaoDTO::fromEntity)
                .collect(Collectors.toList());

        return new PedidoIsencaoResponseDTO(
                pedido.getId(),
                pedido.getStatus(),
                pedido.getEnviandoEm(),
                pedido.getNotice().getId(),
                pedido.getNotice().getTitle(), // Buscando dados de uma entidade relacionada
                pedido.getAplicante().getId(),
                arquivosDTO
        );
    }
}