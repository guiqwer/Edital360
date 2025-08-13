package com.ifce.edital360.dto.recuperarsenha;

import com.ifce.edital360.dto.cadastro.validation.PasswordMatches;
import com.ifce.edital360.model.enums.RecoveryChannel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;


@PasswordMatches
public record PasswordResetRequest(
        @NotBlank String code,
        RecoveryChannel channel,
        @NotBlank @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
                message = "Senha deve ter 8+ caracteres, letras maiúsculas e minúsculas e números")
        String senha,
        @NotBlank String confirmarSenha
) {
}
