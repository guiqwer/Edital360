package com.ifce.edital360.dto.recuperarsenha;

import com.ifce.edital360.model.enums.RecoveryChannel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PasswordRecoveryDto(

        @NotBlank(message = "CPF é obrigatório.")
        String cpf,

        @NotNull(message = "Canal de recuperação é obrigatório.")
        RecoveryChannel channel,

        @NotBlank(message = "Token do reCAPTCHA é obrigatório.")
        String recaptchaToken
        ) {
}
