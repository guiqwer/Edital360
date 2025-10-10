package com.ifce.edital360.controller.isencao;

import com.ifce.edital360.dto.isencao.PedidoIsencaoResponseDTO;
import com.ifce.edital360.model.enums.StatusIsencao;
import com.ifce.edital360.model.user.User;
import com.ifce.edital360.service.isencao.PedidoInsecaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pedidos-isencao")
public class PedidoIsencaoController {

    private final PedidoInsecaoService pedidoInsecaoService;

    public PedidoIsencaoController(PedidoInsecaoService pedidoInsecaoService) {
        this.pedidoInsecaoService = pedidoInsecaoService;
    }


    @PostMapping(path = "/editais/{noticeId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PedidoIsencaoResponseDTO> criarPedido(
            @PathVariable UUID noticeId,
            @AuthenticationPrincipal User usuarioLogado, // Pega o usuário do token!
            @RequestPart("arquivos") List<MultipartFile> arquivos) throws IOException {

        PedidoIsencaoResponseDTO novoPedido = pedidoInsecaoService.criarPedidoIsencao(noticeId, usuarioLogado, arquivos);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/pedidos-isencao/{id}") // Constrói a URI do novo pedido
                .buildAndExpand(novoPedido.id())
                .toUri();

        return ResponseEntity.created(location).body(novoPedido);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoIsencaoResponseDTO> getPedidoPorId(@PathVariable UUID id) {
        PedidoIsencaoResponseDTO response = pedidoInsecaoService.getPedidoById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza o status do pedido de insenção baseado no id")
    public ResponseEntity<PedidoIsencaoResponseDTO> AtualizarStatusPedidoIsencao(@PathVariable UUID id, @Parameter StatusIsencao statusIsencao){
        PedidoIsencaoResponseDTO response = pedidoInsecaoService.atualizarStatusPedidoById(id, statusIsencao);
        return ResponseEntity.ok(response);
    }
}