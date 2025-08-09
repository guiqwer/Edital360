package com.ifce.edital360.dto.cadastro;

import com.ifce.edital360.model.user.Sexo;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CadastroCompletoRequest(
        @NotBlank String cpf,
        @NotBlank String nomeCompleto,
        @NotNull LocalDate dataNascimento,
        @NotBlank String sexo,
        @NotBlank String nomePai,
        @NotBlank String nomeMae,
        @NotBlank String escolaridade,
        @NotBlank String identidade,
        @NotBlank String ufIdentidade,
        @NotBlank String cep,
        @NotBlank String uf,
        @NotBlank String cidade,
        @NotBlank String bairro,
        @NotBlank String logradouro,
        String complemento,
        @NotBlank String numeroCasa,
        @NotBlank String telefoneDdd,
        @NotBlank String telefoneNumero,
        @NotBlank String celularDdd,
        @NotBlank String celularNumero,
        @Email String email,
        @Email String confirmarEmail,
        @NotBlank String senha,
        @NotBlank String confirmarSenha
) {
}
