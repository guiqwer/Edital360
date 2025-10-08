package com.ifce.edital360.dto.deletarconta;

import jakarta.validation.constraints.NotBlank;

public record AccountDeleteDTO(
        @NotBlank(message = "A senha é obrigatória para excluir a conta.")
        String password
) {}