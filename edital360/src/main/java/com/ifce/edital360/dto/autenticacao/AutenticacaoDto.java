package com.ifce.edital360.dto.autenticacao;

import jakarta.validation.constraints.NotBlank;

public record AutenticacaoDto(
        @NotBlank String cpf,
        @NotBlank String password
) {}

