package com.ifce.edital360.dto.cadastro;

import com.ifce.edital360.dto.cadastro.validation.PasswordMatches;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

@PasswordMatches
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
        String telefoneDdd,
        String telefoneNumero,
        @NotBlank String celularDdd,
        @NotBlank String celularNumero,
        @Email String email,
        @Email String confirmarEmail,
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
                message = "Senha deve ter 8+ caracteres, letras maiúsculas e minúsculas e números")
        String senha,
        @NotBlank String confirmarSenha
) {
}
