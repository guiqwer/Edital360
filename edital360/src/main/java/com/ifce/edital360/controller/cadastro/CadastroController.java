package com.ifce.edital360.controller.cadastro;

import com.ifce.edital360.dto.cadastro.CadastroCompletoRequest;
import com.ifce.edital360.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cadastro")
public class CadastroController {

    @Autowired
    private UserService userService;

    @GetMapping("/verificar")
    public ResponseEntity<?> verificarCpfCep(@RequestParam String cpf, @RequestParam String cep) {
        userService.verificacaoCpfCep(cpf, cep);
        return ResponseEntity.ok("CPF e CEP válidos.");
    }

    @PostMapping("/completar")
    public ResponseEntity<?> completarCadastro(@RequestBody CadastroCompletoRequest dto) {
        userService.cadastrarUsuario(dto);
        return ResponseEntity.ok("Cadastro concluído com sucesso.");
    }
}
