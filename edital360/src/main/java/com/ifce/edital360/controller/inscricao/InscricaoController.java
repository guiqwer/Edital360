package com.ifce.edital360.controller.inscricao;

import com.ifce.edital360.service.isencao.PedidoInsecaoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inscricao")
public class InscricaoController {

    private final PedidoInsecaoService pedidoInsecaoService;

    public InscricaoController(PedidoInsecaoService pedidoInsecaoService) {
        this.pedidoInsecaoService = pedidoInsecaoService;
    }
}
