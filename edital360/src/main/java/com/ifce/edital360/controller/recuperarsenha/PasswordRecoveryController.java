package com.ifce.edital360.controller.recuperarsenha;

import com.ifce.edital360.config.security.RecaptchaService;
import com.ifce.edital360.dto.recuperarsenha.PasswordRecoveryDto;
import com.ifce.edital360.dto.recuperarsenha.PasswordResetRequest;
import com.ifce.edital360.dto.responsegenerico.ApiResponse;
import com.ifce.edital360.service.user.PasswordRecoveryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recuperacao/password")
public class PasswordRecoveryController {


    @Autowired
    private PasswordRecoveryService recoveryService;

    @Autowired
    private RecaptchaService recaptchaService;

    /**
     * Endpoint para solicitar o código de recuperação
     * Recebe o CPF e o canal (EMAIL ou SMS)
     */
    @PostMapping("/request")
    public ResponseEntity<ApiResponse> requestRecoveryCode(
            @Valid @RequestBody PasswordRecoveryDto dto) {

        if (!recaptchaService.validacaoRecaptcha(dto.recaptchaToken())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse("ERROR", "Falha na validação do reCAPTCHA"));
        }

        recoveryService.sendRecoveryCode(dto);
        return ResponseEntity.ok(new ApiResponse("SUCCESS", "Código enviado para o e-mail do usuário"));
    }


    /**
     * Endpoint para resetar a senha usando o código recebido
     */
    @PostMapping("/reset")
    public ResponseEntity<ApiResponse> resetPassword(@Valid @RequestBody PasswordResetRequest dto) {
        recoveryService.resetPassword(dto);
        return ResponseEntity.ok(new ApiResponse("SUCCESS", "Senha redefinida com sucesso"));
    }
}