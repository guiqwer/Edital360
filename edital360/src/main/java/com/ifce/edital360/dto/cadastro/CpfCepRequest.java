package com.ifce.edital360.dto.cadastro;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CpfCepRequest(
        @NotBlank(message = "CPF é obrigatório.")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 dígitos")
        String cpf,

        @NotBlank(message = "CEP é obrigatório")
        @Pattern(regexp = "\\d{8}", message = "CEP deve conter 8 dígitos")
        String cep
) {}
